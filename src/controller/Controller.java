package controller;

import model.Fish;
import model.Model;
import view.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class Controller {

    private Window window = new Window();
    private Model model = new Model();

    public Controller() { }

    public void start() {
        window.loadUser(new LoadUsers());
    }

    class LoadUsers implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<String> users = model.getAllUsers();
            window.popupUsers(users);
            loadFishes(window.getUser());
        }

        private void loadFishes(String user) {
            Map<Integer, Fish> fishes = model.loadFishes(user);
            window.updateDiary(fishes);
        }
    }
}
