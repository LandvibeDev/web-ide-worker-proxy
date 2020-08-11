package com.webIDE.proxy.terminal;

import com.webIDE.proxy.terminal.model.Terminal;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/terminal/project/{pid}")
public class TerminalController {

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Terminal RunRealtimeCommand() {
        Terminal terminal = new Terminal("node hello.js", "hello 울림ide!");

        return terminal;
    }
}
