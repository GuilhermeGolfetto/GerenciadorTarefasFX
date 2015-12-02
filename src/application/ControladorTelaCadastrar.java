package application;

import dao.TarefaDAO;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Tarefa;

public class ControladorTelaCadastrar extends Application {

	@FXML
	public Pane root;
	@FXML
	public TextField txtNome;
	@FXML
	public TextArea txtDesc;
	@FXML
	public TextField txtPrazo;
	@FXML
	public RadioButton rdBaixa;
	@FXML
	public RadioButton rdMedia;
	@FXML
	public RadioButton rdAlta;
	@FXML
	public CheckBox cBoxConc;
	@FXML
	public Button btnCadastrar;

	int pri = 0;

	public void baixaClick() {
		pri = 1;
	}

	public void mediaClick() {
		pri = 2;
	}

	public void altaClick() {
		pri = 3;
	}

	public void btnCadastrarClick() {
		Tarefa t = new Tarefa();
		t.setNome(txtNome.getText());
		t.setDescricao(txtDesc.getText());
		t.setPrazo(txtPrazo.getText());
		switch (pri) {
		case 1:
			t.setPrioridade("Baixa");
			break;
		case 2:
			t.setPrioridade("Media");
			break;
		case 3:
			t.setPrioridade("Alta");
		}
		t.setConcluida(cBoxConc.isSelected() ? "sim" : "não");
		new TarefaDAO().adciona(t);
		Main m = new Main();
		m.start(new Stage());
		Stage stage = (Stage) btnCadastrar.getScene().getWindow();
		stage.close();
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource("telaAdicionar.fxml"));
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.show();

	}

	@Override
	public void init() throws Exception {

	}

	@Override
	public void stop() throws Exception {

	}

}
