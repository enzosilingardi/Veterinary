package Model;

public class ComboItem {
private String key;              //Label visible del ComboBox
    
    private String value;           //Valor del ComboBox

    public ComboItem(String key, String value)       //Genera el label que se verá en el ComboBox y el valor del objeto seleccionado
    {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString()
    {
        return key;
    }

    public String getKey()
    {
        return key;
    }

    public String getValue()
    {
        return value;
    }
}
