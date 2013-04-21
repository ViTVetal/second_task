import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tasks")
public class Account {
    @DatabaseField
    private String text;
    @DatabaseField
    private int prioritet;
    @DatabaseField (generatedId=true)
    private int id;
    
    public Account() {
        // ORMLite needs a no-arg constructor 
    }
    public Account(String text, int prioritet) {
        this.text = text;
        this.prioritet = prioritet;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public int getPrioritet() {
        return prioritet;
    }
    public void setPrioritet(int prioritet) {
        this.prioritet = prioritet;
    }
}
