package com.terapia.financeira.model.dao;

import com.terapia.financeira.db.DataBase;
import com.terapia.financeira.model.dao.impl.GoalsDaoJdbc;

public class DaoFactory {
    public static GoalsDao createGoalsDao(){
        return new GoalsDaoJdbc(DataBase.getConnection());
    }
}
