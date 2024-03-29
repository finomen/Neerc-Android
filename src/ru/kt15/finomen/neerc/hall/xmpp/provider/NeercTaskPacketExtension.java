package ru.kt15.finomen.neerc.hall.xmpp.provider;

import org.jivesoftware.smack.packet.PacketExtension;

import ru.kt15.finomen.neerc.hall.Task;
import ru.kt15.finomen.neerc.hall.xmpp.utils.XmlUtils;

/**
 * @author Evgeny Mandrikov
 */
public class NeercTaskPacketExtension implements PacketExtension {
    private Task task;
    
    public NeercTaskPacketExtension() {
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getElementName() {
        return "x";
    }

    public String getNamespace() {
        return XmlUtils.NAMESPACE_TASKS;
    }

    public String toXML() {
        StringBuilder buf = new StringBuilder();
        buf.append("<").append(getElementName()).append(" xmlns=\"").append(getNamespace()).append("\">");
        if (getTask() != null) {
            // TODO
        }
        buf.append("</").append(getElementName()).append(">");
        return buf.toString();
    }
}
