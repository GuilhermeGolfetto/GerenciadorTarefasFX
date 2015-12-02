package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import dao.ExportadorXML;
import dao.TarefaDAO;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import model.Tarefa;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class Main extends Application {

	public ObservableList<Tarefa> tarefas = new TarefaDAO().lista();

	public TableView tv = new TableView(tarefas);

	@Override
	public void start(Stage primaryStage) {

		Group group = new Group();

		Scene scene = new Scene(group, 725, 510);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		final VBox vbox = new VBox(tv);
		Label lblInicio = new Label("Gerenciador de Tarefas");

		Label lblProgresso = new Label();

		Label lblFooter = new Label("Uma produção Guilherme Golfetto");
		Button btnCadastrar = new Button("Cadastrar");
		Button btnExportar = new Button("Exportar XML");

		TableColumn nomeColumn = new TableColumn("Tarefa");
		nomeColumn.setMinWidth(110);
		nomeColumn.setCellValueFactory(new PropertyValueFactory("nome"));
		TableColumn descricaoColumn = new TableColumn("Descrição");
		descricaoColumn.setMinWidth(200);
		descricaoColumn.setCellValueFactory(new PropertyValueFactory("descricao"));
		TableColumn prazoColumn = new TableColumn("Prazo");
		prazoColumn.setMinWidth(110);
		prazoColumn.setCellValueFactory(new PropertyValueFactory("prazo"));
		TableColumn prioridadeColumn = new TableColumn("Prioridade");
		prioridadeColumn.setMinWidth(110);
		prioridadeColumn.setCellValueFactory(new PropertyValueFactory("prioridade"));
		TableColumn concluidaColumn = new TableColumn("Concluida");
		concluidaColumn.setMinWidth(110);
		concluidaColumn.setCellValueFactory(new PropertyValueFactory("concluida"));

		List<TableColumn> columns = new ArrayList<>();

		tv.getColumns().addAll(nomeColumn, descricaoColumn, prazoColumn, prioridadeColumn, concluidaColumn);

		columns.add(nomeColumn);
		columns.add(descricaoColumn);
		columns.add(prazoColumn);
		columns.add(prioridadeColumn);
		columns.add(concluidaColumn);

		vbox.setId("vbox");

		lblInicio.setId("lblInicio");

		lblProgresso.setId("lblprogresso");

		double total = 0.0;

		for (@SuppressWarnings("unused")
		Tarefa tarefa : tarefas) {
			total++;
		}

		Label lblTotal = new Label();
		
		String umTarefa = "Você tem 1 tarefa cadastrada";

		String zeroTarefa = "Você não tem tarefa Cadastrada";

		if (total == 0) {
			lblTotal.setText(zeroTarefa);
			lblTotal.setId("lblTotal");
		} else if (total == 1) {
			lblTotal.setText(umTarefa);
			lblTotal.setId("lblTotal");
		} else {
			lblTotal.setText(String.format("Você tem: %1$,.0f tarefas cadastradas", total));
			lblTotal.setId("lblTotal");
		}
		
		lblFooter.setId("lblFooter");

		btnCadastrar.setId("btnCadastrar");
		btnCadastrar.setOnAction(event -> {
			try {
				ControladorTelaCadastrar cr = new ControladorTelaCadastrar();
				cr.start(new Stage());
				primaryStage.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		btnExportar.setId("btnExportar");

		btnExportar.setOnAction(event -> {
			exportarXML(tarefas);
		});

		group.getChildren().addAll(vbox, btnCadastrar, lblInicio, lblTotal, lblFooter,btnExportar);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Gerenciador de Tarefas");
		primaryStage.setResizable(false);
		primaryStage.show();

	}

	public void exportarXML(ObservableList<Tarefa> tarefas) {
		try {
			new ExportadorXML().paraXML(tarefas);
		} catch (IOException e) {
			System.out.println("Erro ao exportar: " + e);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
