/*
 * Copyright (C) 2015-2016 L2J EventEngine
 *
 * This file is part of L2J EventEngine.
 *
 * L2J EventEngine is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * L2J EventEngine is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.datapack.eventengine.datatables;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import com.l2jserver.datapack.eventengine.model.entity.Player;
import com.l2jserver.gameserver.util.IXmlReader;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.l2jserver.gameserver.model.holders.SkillHolder;

/**
 * This class is responsible for:<br>
 * Load the list of buffs that can be used in events. Manage the list of buffs that were selected by the characters.
 * @author fissban
 */
public class BuffListData implements IXmlReader
{
	private static final Logger LOGGER = Logger.getLogger(BuffListData.class.getName());
	// Buff List
	private final List<SkillHolder> _buffList = new ArrayList<>();
	// Characters Buff List
	private final Map<Integer, Set<SkillHolder>> _buffsPlayers = new ConcurrentHashMap<>();
	
	public BuffListData()
	{
		load();
	}
	
	@Override
	public void load()
	{
		parseDatapackFile("data/eventengine/xml/buff_list.xml");
		LOGGER.info("Loaded Buffs: " + _buffList.size());
	}
	
	@Override
	public void parseDocument(Document doc)
	{
		for (Node n = doc.getFirstChild().getFirstChild(); n != null; n = n.getNextSibling())
		{
			if ("buff".equalsIgnoreCase(n.getNodeName()))
			{
				int buffId = Integer.parseInt(n.getAttributes().item(0).getNodeValue());
				int buffLvl = Integer.parseInt(n.getAttributes().item(1).getNodeValue());
				
				_buffList.add(new SkillHolder(buffId, buffLvl));
			}
		}
	}
	
	/**
	 * List all possible buffs for events.
	 * @return List<SkillHolder>
	 */
	public List<SkillHolder> getAllBuffs()
	{
		return _buffList;
	}
	
	/**
	 * Get buffs lists selected by a character.
	 * @param player
	 * @return List<SkillHolder>
	 */
	public Set<SkillHolder> getBuffsPlayer(Player player)
	{
		if (_buffsPlayers.containsKey(player.getObjectId()))
		{
			return _buffsPlayers.get(player.getObjectId());
		}
		return Collections.emptySet();
	}
	
	/**
	 * Check if a character has a particular skill.
	 * @param player
	 * @param sh
	 * @return List<SkillHolder>
	 */
	public boolean getBuffPlayer(Player player, SkillHolder sh)
	{
		if (_buffsPlayers.containsKey(player.getObjectId()))
		{
			for (SkillHolder aux : _buffsPlayers.get(player.getObjectId()))
			{
				if (aux.getSkill() == sh.getSkill())
				{
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Remove a buff from the listing of a player.
	 * @param player
	 * @param sh
	 */
	public void deleteBuffPlayer(Player player, SkillHolder sh)
	{
		for (SkillHolder aux : _buffsPlayers.get(player.getObjectId()))
		{
			if (aux.getSkill() == sh.getSkill())
			{
				_buffsPlayers.get(player.getObjectId()).remove(aux);
				break;
			}
		}
	}
	
	/**
	 * Add a buff to the listing of a player.
	 * @param player
	 * @param sh
	 */
	public void addBuffPlayer(Player player, SkillHolder sh)
	{
		if (_buffsPlayers.containsKey(player.getObjectId()))
		{
			_buffsPlayers.get(player.getObjectId()).add(sh);
		}
		else
		{
			Set<SkillHolder> aux = new HashSet<>(1);
			aux.add(sh);
			
			_buffsPlayers.put(player.getObjectId(), aux);
		}
	}
	
	/**
	 * Clean buff list of a character without deleting the character from the list.
	 * @param player
	 */
	public void clearBuffsPlayer(Player player)
	{
		if (_buffsPlayers.containsKey(player.getObjectId()))
		{
			_buffsPlayers.get(player.getObjectId()).clear();
		}
	}
	
	public static BuffListData getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private static class SingletonHolder
	{
		protected static final BuffListData _instance = new BuffListData();
	}
}