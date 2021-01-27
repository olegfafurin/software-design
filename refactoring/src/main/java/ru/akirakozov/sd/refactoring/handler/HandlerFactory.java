package ru.akirakozov.sd.refactoring.handler;

import ru.akirakozov.sd.refactoring.Database;

import javax.servlet.http.HttpServletRequest;

/**
 * created by imd on 27.01.2021
 */

public class HandlerFactory {

    public QueryHandler getHandler(HttpServletRequest request, Database db) {
        String command = request.getParameter("command");

        if ("max".equals(command)) {
            return new MaxQueryHandler(db);
        } else if ("min".equals(command)) {
            return new MinQueryHandler(db);
        }
        if ("sum".equals(command)) {
            return new SumHandler(db);
        }
        if ("count".equals(command)) {
            return new CountQueryHandler(db);
        } else {
            return new UnknownQueryHandler(db, command);
        }
    }
}
