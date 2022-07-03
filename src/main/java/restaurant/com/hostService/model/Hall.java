package restaurant.com.hostService.model;

import java.util.ArrayList;
import java.util.List;

public class Hall {
    private List<Table> tables = new ArrayList<Table>();
    private Integer totalNumberOfTables;

    public Hall(Integer p_totalNumberOfTables) {
        for(int i = 0; i < p_totalNumberOfTables; i++)
            tables.add(new Table(Long.valueOf(i+1)));

    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    public Integer getTotalNumberOfTables() {
        return totalNumberOfTables;
    }

    public void setTotalNumberOfTables(Integer totalNumberOfTables) {
        this.totalNumberOfTables = totalNumberOfTables;
    }

    @Override
    public String toString() {
        return "tables=" + tables +
                ", totalNumberOfTables=" + totalNumberOfTables;
    }
}
