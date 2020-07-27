package cc.vimc.mcbot.bot.plugins;

import cc.moecraft.icq.command.CommandProperties;
import cc.moecraft.icq.command.interfaces.EverywhereCommand;
import cc.moecraft.icq.event.events.message.EventMessage;
import cc.moecraft.icq.sender.message.MessageBuilder;
import cc.moecraft.icq.user.User;
import cc.vimc.mcbot.enums.Commands;
import cc.vimc.mcbot.enums.ConstantMessages;
import cc.vimc.mcbot.mapper.CoolQUserMapper;
import cc.vimc.mcbot.pojo.CoolQUser;
import cc.vimc.mcbot.rcon.RconCommand;
import cc.vimc.mcbot.utils.SpringContextUtil;

import java.util.ArrayList;

public class ListPlayer implements EverywhereCommand {

    @Override
    public String run(EventMessage event, User sender, String command, ArrayList<String> args) {
        MessageBuilder messageBuilder = new MessageBuilder();
        CoolQUserMapper coolQUserMapper = SpringContextUtil.getBean(CoolQUserMapper.class);
        CoolQUser coolQUser = coolQUserMapper.selectQQExist(sender.getId());
        if (coolQUser==null){
            messageBuilder.add(ConstantMessages.PLAYER_NOT_EXIST.getMessage());
            return messageBuilder.toString();
        }
        messageBuilder.add(RconCommand.send(Commands.LIST.getCommand()));
        return messageBuilder.toString();
    }

    @Override
    public CommandProperties properties() {
        return new CommandProperties(Commands.LIST.getCommand());
    }


}