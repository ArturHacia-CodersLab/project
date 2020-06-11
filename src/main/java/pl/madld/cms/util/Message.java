package pl.madld.cms.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Message {
    private String text;
    private MessageType type;
}
