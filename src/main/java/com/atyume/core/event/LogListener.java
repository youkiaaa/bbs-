package com.atyume.core.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import com.atyume.modules.system.po.Log;
import com.atyume.modules.system.service.LogService;

/**
 * 日志事件监听
 */
@Component
public class LogListener {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LogService logService;

    @EventListener
    public void handleLogEvent(Log log) {
        logger.info("Handling log event.", log);
        logService.create(log);
    }

}
