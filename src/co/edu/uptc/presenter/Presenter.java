package co.edu.uptc.presenter;

import co.edu.uptc.model.Department;
import co.edu.uptc.model.Inhabitant;
import co.edu.uptc.view.View;

public class Presenter {
    private Department department;
    private View view;

    public Presenter() {
        department = new Department("BOYACÁ");
        view = new View();
    }

    public int showMenu()  {
        int option = 0;
        try {
            option = Integer.parseInt(view.readData(view.showMenu(department.getName())));
        } catch (NumberFormatException e) {
            view.showMessage("Input a valid option");
            run();
        }
        return option;
    }

    public void run() {

            int option = showMenu();
            switch (option) {
                case 1:
                    addInhabitant();
                    break;
                case 2:
                    showInhabitants();
                    break;
                case 3:
                    calculateMostPopulus();
                    break;
                case 0:
                    view.showMessage("Finishing program");
                    System.exit(0);
                    break;
                default:
                    view.showMessage("Input a valid option");
                    break;
            }
            do {
                run();
            } while (option != 4);


    }

    private void addInhabitant() {
        String town = view.readData("Input the name of the town: ");
        String name = view.readData("Input the name of the inhabitant: ");
        String id = view.readData("Input the id of the inhabitant: ");
        boolean verification =  department.addInhabitantInTown(town, new Inhabitant(id, name));
        if (verification) {
            view.showMessage("Inhabitant added successfully");
        } else {
            view.showMessage("The town does not exist");
        }
    }

    private void showInhabitants() {
        String town = view.readData("Input the name of the town: ");
        if (department.searchTownship(town) == null) {
            view.showMessage("The town does not exist");
            return;
        }else {
            for (Inhabitant inhabitant : department.searchTownship(town).getInhabitants()) {
                view.showMessage(inhabitant.toString());
            }
        }

    }

    private void calculateMostPopulus() {
        view.showMessage("The town with the most inhabitants is: " + department.calculateMostPopulus());
    }


}
