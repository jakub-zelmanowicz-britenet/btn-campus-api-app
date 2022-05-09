package pl.britenet.campus;

import pl.britenet.campus.obj.Command;
import pl.britenet.campus.obj.HelloCommand;
import pl.britenet.campus.obj.model.Product;
import pl.britenet.campus.service.CommandService;

import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        CommandService commandService = new CommandService();

        HelloCommand helloCommand = new HelloCommand();

        commandService.registerCommand(helloCommand);

        Product product = new Product(0);
        product.setDescription("");
        product.setName("");

        Optional<Command> oCommand = commandService.getCommand("hello");
        if (oCommand.isPresent()) {
            oCommand.get().perform();
        }
        else {
            System.out.println("Nieznana komenda.");
        }
    }

}
