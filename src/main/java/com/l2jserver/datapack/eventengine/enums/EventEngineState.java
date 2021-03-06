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
package com.l2jserver.datapack.eventengine.enums;

/**
 * @author fissban
 */
public enum EventEngineState
{
	/**
	 * Waiting to start voting or registration to the next event.
	 */
	WAITING,
	
	/**
	 * Is not found any events running. It is enabled user registration.
	 */
	VOTING,
	
	/**
	 * Is not found any events running. It is enabled user registration.
	 */
	REGISTER,
	
	/**
	 * Run the most voted event or one randomly.
	 */
	RUN_EVENT,
	
	/**
	 * Have a running event. Is not enabled user registration. The vote is not able to events.
	 */
	RUNNING_EVENT,
	
	/**
	 * Event finishes.
	 */
	EVENT_ENDED
}