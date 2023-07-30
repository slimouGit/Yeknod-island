package net.slimou.askyeknod;

import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class YeknodService {

    private static final Logger logger = Logger.getLogger(YeknodService.class.getName());

    public String startRequest(String text) {
        logger.info(String.format("request %s", text));
        return text;
    }
}
