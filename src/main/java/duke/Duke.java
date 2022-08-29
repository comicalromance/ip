package duke;

import duke.command.Command;

public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public static final String FOLDER_LOCATION = "data";
    public static final String FILE_LOCATION = "data/duke.txt";
    private boolean toClose = false;

    public Duke(String filePath, String folderPath) {
        ui = new Ui();
        storage = new Storage(filePath, folderPath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.print(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {

        ui.printGreetings();

        while(true) {
            String fullCommand = ui.readCommand();
            try {
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                if (c.isClose()) break;
                storage.save(tasks);
            } catch (Exception e){
                ui.print(e.getMessage());
            }
        }
        ui.close();
    }

    public static void main(String[] args) {
        new Duke(FILE_LOCATION, FOLDER_LOCATION).run();
    }
}
