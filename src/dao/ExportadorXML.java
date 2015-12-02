package dao;

import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import model.Tarefa;

public class ExportadorXML {
	
	public void paraXML(List<Tarefa> tarefas)throws IOException{
		
		XStream xs = new XStream(new DomDriver());
		String xml = xs.toXML(tarefas);
		
		
		PrintStream ps = new PrintStream("tarefas.xml");
		ps.print(xml);
		
		ps.close();
	}

}
