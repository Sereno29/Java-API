package com.apiaccess.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ClosingEvent;
import com.google.gwt.user.client.Window.ClosingHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;

public class Apiaccess implements EntryPoint, ValueChangeHandler<String> {

	static final int DECK_CSV = 0;
	static final int DECK_QUERIES = 1;
	static final int DECK_INSERT = 2;
	static final int DECK_DELETE = 3;
	
	static final String TOKEN_CSV = "Popular base de dados com CSV";
	static final String TOKEN_QUERIES = "Consultas";
	static final String TOKEN_INSERT = "Inserir Cidade";
	static final String TOKEN_DELETE = "Deletar Cidade";
	
	private static final String LOGO_IMAGE_NAME = "restAPI.jpeg";
	
	private static final String BASE_URL = "http://localhost:8080/";
	
	private Label errorMsgLabel = new Label();
	
	enum Pages {
		CSV(DECK_CSV, TOKEN_CSV), QUERIES(DECK_QUERIES, TOKEN_QUERIES), INS(DECK_INSERT, TOKEN_INSERT), DEL(DECK_DELETE, TOKEN_DELETE);
		
		private int val;
		private String text;
		
		int getVal(){return val;}
		String getText(){return text;}

		Pages(int val, String text) {
			this.val = val;
			this.text = text;
		};
	}

	TabLayoutPanel content;
	
	Image logo;
	
	private void insertLogo(){
		logo = new Image(GWT.getModuleBaseURL() + "../" + LOGO_IMAGE_NAME){
			public void onBrowserEvent(Event evt){
				evt.preventDefault();
				
				super.onBrowserEvent(evt);
			}
		};
	}
	

	private void setUpEventHandling(){
		content.addSelectionHandler(new SelectionHandler<Integer>(){
			public void onSelection(SelectionEvent<Integer> event) {
				Integer tabSelected = event.getSelectedItem();
				History.newItem(Pages.values()[tabSelected].getText());
			}
		});
		
	}
	
	HTMLPanel csvPanel;
	HTMLPanel queriesPanel;
	HTMLPanel insertPanel;
	HTMLPanel deletePanel;
	HTMLPanel responseScroll;
	TextArea responseText;

	private void buildTabContent(){
		
		setupCsvTab();
		setupQueriesTab();
		setupInsertTab();
		setupDeleteTab();
		String html = 
			    "<div id='text' >" +
			      "<p id='response' >" +
			      "</p>" +
			    "</div>";
		
		responseScroll = new HTMLPanel(html);
		responseText = new TextArea();
		responseText.setHeight("600px");
		responseText.setWidth("600px");
		responseText.setReadOnly(true);
		responseText.setValue("Hello, World!");
		responseText.getElement().setId("response");
		responseScroll.add(responseText);
		
		csvPanel.addStyleName("htmlPanel");
		queriesPanel.addStyleName("htmlPanel");
		insertPanel.addStyleName("htmlPanel");
		deletePanel.addStyleName("htmlPanel");
		
		content = new TabLayoutPanel(2.5, Unit.EM);

		content.add(csvPanel, Pages.CSV.getText());
		content.add(queriesPanel, Pages.QUERIES.getText());
		content.add(insertPanel, Pages.INS.getText());
		content.add(deletePanel, Pages.DEL.getText());
		
		content.selectTab(DECK_CSV);
	}
	
	private void setupCsvTab() {
		String html = 
			    "<div id='LoginPage' name='LoginPage'>" +
			      "<p id='fileUpload' >" +
			        "<label>Selecionar arquivo:<br/>" +
			      "</p>" +
			      "<p id='submit' class='submit'>" +
			      "</p>" +
			    "</div>";
		
		csvPanel = new HTMLPanel(html);

		FileUpload upload = new FileUpload();
		upload.getElement().setId("fileUpload");
		csvPanel.add(upload, "fileUpload");
		
		// The log in button
		Button submit = new Button("Extrair arquivo csv");
		submit.getElement().setId("submit");
		csvPanel.add(submit, "submit");

		submit.addClickHandler(new ClickHandler() {
			
		   public void onClick(ClickEvent event) {
	             // Perform Validations
			   /* TODO */
		    }
		});
		
	}

	private void setupQueriesTab() {
		String html = 
			    "<div id='QueriesPage' name='QueriesPage'>" +
			      "<p id='listBox' >" +
			        "<label>Selecionar consulta:<br/>" +
			      "</p>" +
			      "<p id='inputIdIbge' >" +
			        "<label>IBGE id:<br/>" +
			      "</p>" +
			      "<p id='inputColumn' >" +
			        "<label>Nome da coluna:<br/>" +
			      "</p>" +
			      "<p id='inputFilter' >" +
			        "<label>Filtro:<br/>" +
			      "</p>" +
			      "<p id='submit' class='submit'>" +
			      "</p>" +
			    "</div>";
		
		queriesPanel = new HTMLPanel(html);
		
		String[] queries = {
				"Capitais", 
				"Estados com maior e menor quantidade de cidades", 
				"Quantidade de cidades por estado", 
				"Cidade com id IBGE",
				"Retornar nome de cidades de um estado",
				"Selecionar coluna filtrada",
				"Quantidade de registros distintos em uma coluna",
				"Distância máxima entre cidades",
				"Quantidade total de registros"
				};

		ListBox dropbox = new ListBox();
		for(int i=0; i<queries.length; i++)
			dropbox.addItem(queries[i]);
		dropbox.getElement().setId("listBox");
		queriesPanel.add(dropbox, "listBox");
		
		TextBox column = new TextBox();
		column.getElement().setId("inputColumn");
		queriesPanel.add(column, "inputColumn");
		
		TextBox filter = new TextBox();
		filter.getElement().setId("inputFilter");
		queriesPanel.add(filter, "inputFilter");
		
		
		TextBox idIbge = new TextBox();
		idIbge.getElement().setId("inputIdIbge");
		queriesPanel.add(idIbge, "inputIdIbge");
		
		// The log in button
		Button submit = new Button("Consultar");
		submit.getElement().setId("submit");
		queriesPanel.add(submit, "submit");

		submit.addClickHandler(new ClickHandler() {
			
		   public void onClick(ClickEvent event) {
	             // Perform Validations
			   RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, BASE_URL.concat("capitals"));
			   try {
				   Request request = builder.sendRequest(null, new RequestCallback() {
					   public void onError(Request request, Throwable exception) {
						   displayError("Couldn't retrieve JSON");
					   }
					   
					   public void onResponseReceived(Request request, Response response) {
						   if(200 == response.getStatusCode()) {
							  responseText.setValue(JsonUtils.safeEval(response.getText()).toString());
						  }else {
							  displayError("Couldn't retrieve JSON");
						  }
					   }
					   
				   });
					   
				   }catch(RequestException e) {
					   displayError("Couldn't retrieve JSON");
			   }
			   
		    }
		});
		
	}
	
	private void setupInsertTab() {
		String html = 
			    "<div id='InsertPage' name='InsertPage'>" +
			      "<p id='inputIdIbgeInsert' >" +
			        "<label>IBGE id:<br/>" +
			      "</p>" +
			      "<p id='inputNameInsert' >" +
			        "<label>Nome:<br/>" +
			      "</p>" +
			      "<p id='inputStateInsert' >" +
			        "<label>Estado:<br/>" +
			      "</p>" +
			      "<p id='inputIsCapitalInsert' >" +
			        "<label>Capital:<br/>" +
			      "</p>" +
			      "<p id='inputLongitudeInsert' >" +
			        "<label>Longitude:<br/>" +
			      "</p>" +  
			      "<p id='inputLatitudeInsert' >" +
			        "<label>Latitude:<br/>" +
			      "</p>" +
			      "<p id='inputAlternativeNameInsert' >" +
			        "<label>Nome alternativo:<br/>" +
			      "</p>" +
			      "<p id='inputMicroregionInsert' >" +
			        "<label>Microregião:<br/>" +
			      "</p>" +
			      "<p id='inputMesoRegionInsert' >" +
			        "<label>Mesoregião:<br/>" +
			      "</p>" +
			      "<p id='submit' class='submit'>" +
			      "</p>" +
			    "</div>";
		
		insertPanel = new HTMLPanel(html);
		
		String[] isCapitalOptions = {
				"true",
				"false"
				};

		ListBox dropbox = new ListBox();
		for(int i=0; i<isCapitalOptions.length; i++)
			dropbox.addItem(isCapitalOptions[i]);
		dropbox.getElement().setId("inputIsCapitalInsert");
		insertPanel.add(dropbox, "inputIsCapitalInsert");
		
		TextBox idIbge = new TextBox();
		idIbge.getElement().setId("inputIdIbgeInsert");
		insertPanel.add(idIbge, "inputIdIbgeInsert");
		
		TextBox state = new TextBox();
		state.getElement().setId("inputStateInsert");
		insertPanel.add(state, "inputStateInsert");
		
		TextBox name = new TextBox();
		name.getElement().setId("inputNameInsert");
		insertPanel.add(name, "inputNameInsert");
		
		TextBox latitude = new TextBox();
		latitude.getElement().setId("inputLatitudeInsert");
		insertPanel.add(latitude, "inputLatitudeInsert");
		
		TextBox longitude = new TextBox();
		longitude.getElement().setId("inputLongitudeInsert");
		insertPanel.add(longitude, "inputLongitudeInsert");
		
		TextBox microregion = new TextBox();
		microregion.getElement().setId("inputMicroregionInsert");
		insertPanel.add(microregion, "inputMicroregionInsert");
		
		TextBox mesoregion = new TextBox();
		mesoregion.getElement().setId("inputMesoRegionInsert");
		insertPanel.add(mesoregion, "inputMesoRegionInsert");
		
		// The log in button
		Button submit = new Button("Consultar");
		submit.getElement().setId("submit");
		insertPanel.add(submit, "submit");

		submit.addClickHandler(new ClickHandler() {
			
		   public void onClick(ClickEvent event) {
	             // Perform Validations
			   /* TODO */
			   
		    }
		});
		
	}
	
	private void setupDeleteTab() {
		String html = 
			    "<div id='QueriesPage' name='QueriesPage'>" +
			      "<p id='inputIdIbge' >" +
			        "<label>IBGE id:<br/>" +
			      "</p>" +
			      "<p id='submit' class='submit'>" +
			      "</p>" +
			    "</div>";
		
		deletePanel = new HTMLPanel(html);
		
		
		TextBox idIbge = new TextBox();
		idIbge.getElement().setId("inputIdIbge");
		deletePanel.add(idIbge, "inputIdIbge");
		
		// The log in button
		Button submit = new Button("Consultar");
		submit.getElement().setId("submit");
		deletePanel.add(submit, "submit");

		submit.addClickHandler(new ClickHandler() {
			
		   public void onClick(ClickEvent event) {
	             // Perform Validations
			   /* TODO */
		    }
		});
	}

	private void styleTabPanelUsingUIObject(){
		csvPanel.setHeight("200px");
		queriesPanel.setHeight("400px");
		insertPanel.setHeight("800px");
		deletePanel.setHeight("200px");
		content.setHeight("220px");
	}

	private void setUpGui() {
		buildTabContent();
		insertLogo();
		styleTabPanelUsingUIObject();
		RootPanel logoSlot = RootPanel.get("logo");
		if (logoSlot!=null)logoSlot.add(logo);
		RootPanel contentSlot = RootPanel.get("content");
		if (contentSlot!=null) contentSlot.add(content);
		RootPanel scrollSlot = RootPanel.get("scroll");
		if(scrollSlot!=null) scrollSlot.add(responseScroll);
	}

	public void onModuleLoad() {
		setUpGui();		
		setUpHistoryManagement();
		setUpEventHandling();
	}

	public void setUpHistoryManagement(){
		History.addValueChangeHandler(this);
		History.fireCurrentHistoryState();
		Window.addWindowClosingHandler(new ClosingHandler(){
			public void onWindowClosing(ClosingEvent event) {
				event.setMessage("Ran out of history.  Now leaving application, is that OK?");
			}
		});
	}

	public void onValueChange(ValueChangeEvent<String> event) {
		String page = event.getValue().trim();
		if ((page == null) || (page.equals("")))
			showCsv();
		else if (page.equals(Pages.QUERIES.getText())) {
			showQueries();
			content.setHeight("420px");
		}else if (page.equals(Pages.INS.getText())) {
			showInsert();;
			content.setHeight("820px");
		}else if (page.equals(Pages.DEL.getText())) {
			showDelete();
			content.setHeight("220px");
		}else {
			showCsv();
			content.setHeight("220px");
		}
	}
	
	private void showInsert() {
		content.selectTab(Pages.INS.getVal());
	}
	
	private void showDelete() {
		content.selectTab(Pages.DEL.getVal());
	}

	private void showCsv() {
		content.selectTab(Pages.CSV.getVal());
	}

	private void showQueries() {
		content.selectTab(Pages.QUERIES.getVal());
	}
	
	private void displayError(String error) {
		errorMsgLabel.setText("Error: " + error);
		errorMsgLabel.setVisible(true);
	}
}
