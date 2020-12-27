import main.CWire;
import main.CWireOptions;

public class Main {
    public static void main(String[] args) {
        CWireOptions options = new CWireOptions();
        options.setApiURL("http://localhost:5000");
        CWire cwire = CWire.init("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ2ZXJzaW9uIjoidjEiLCJ0eXBlIjoiYXBpLWNsaWVudCIsInBheWxvYWQiOiI1ZjdkZjI5N2VkNjVjNzFiZWRmNzYzYTQiLCJpYXQiOjE2MDIwODk2MjN9.xBkcWpUECB1NQ1bCrjvYGJ1pqp7MILZdbG-m7eyKMbU", options);
    }
}
