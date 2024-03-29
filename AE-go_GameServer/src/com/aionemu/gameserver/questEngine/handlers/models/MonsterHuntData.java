/*
 * This file is part of aion-unique <aion-unique.org>.
 *
 * aion-unique is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * aion-unique is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with aion-unique.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.aionemu.gameserver.questEngine.handlers.models;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import javolution.util.FastMap;

import com.aionemu.gameserver.questEngine.handlers.QuestHandlers;
import com.aionemu.gameserver.questEngine.handlers.template.MonsterHunt;
import com.aionemu.gameserver.questEngine.handlers.template.MonsterHunt.MonsterInfo;

/**
 * @author MrPoke
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MonsterHuntData", propOrder = { "monsterInfos" })
public class MonsterHuntData extends QuestScriptData
{

	@XmlElement(name = "monster_infos", required = true)
	protected List<MonsterInfos>	monsterInfos;
	@XmlAttribute(name = "start_npc_id")
	protected int					startNpcId;

	@Override
	public void register()
	{
		FastMap<Integer, MonsterInfo> monsterInfo = new FastMap<Integer, MonsterInfo>();
		for(MonsterInfos mi : monsterInfos)
			monsterInfo.put(mi.getNpcId(), new MonsterInfo(mi.getVarId(), mi.getMaxKill()));
		MonsterHunt template = new MonsterHunt(id, startNpcId, monsterInfo);
		QuestHandlers.addQuestHandler(template);
	}

}
