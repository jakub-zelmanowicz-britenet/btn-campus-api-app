package pl.britenet.campus.obj;

public class HelloCommand extends Command {

    public HelloCommand() {
        super("hello");
    }

    @Override
    public void perform() {
        System.out.println("Hello World!");
    }

}
