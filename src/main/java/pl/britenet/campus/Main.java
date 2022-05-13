package pl.britenet.campus;

import pl.britenet.campus.builder.ProductBuilder;
import pl.britenet.campus.obj.Command;
import pl.britenet.campus.obj.HelloCommand;
import pl.britenet.campus.obj.model.Product;
import pl.britenet.campus.obj.productCommands.RetrieveProductCommand;
import pl.britenet.campus.service.CommandService;
import pl.britenet.campus.service.ProductService;
import pl.britenet.campus.service.database.DatabaseService;

import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        DatabaseService databaseService = new DatabaseService();

        ProductService productService = new ProductService(databaseService);

        CommandService commandService = new CommandService();
        commandService.registerCommand(new HelloCommand());
        commandService.registerCommand(new RetrieveProductCommand(productService));

        Product product = new ProductBuilder(0)
                .setName("Test Name")
                .setDescription("Test Description")
                .setCategoryId(1)
                .getProduct();

        productService.create(product);

//        Optional<Command> oCommand = commandService.getCommand("retrieve-product");
//        if (oCommand.isPresent()) {
//            oCommand.get().perform();
//        }
//        else {
//            System.out.println("Nieznana komenda.");
//        }
    }

}
