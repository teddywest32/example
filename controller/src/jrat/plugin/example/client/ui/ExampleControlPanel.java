package jrat.plugin.example.client.ui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;

import jrat.api.Client;
import jrat.api.ui.BaseControlPanel;
import jrat.plugin.example.client.AskPacket;
import jrat.plugin.example.client.ExampleClientPlugin;

@SuppressWarnings("serial")
public class ExampleControlPanel extends BaseControlPanel {

	/**
	 * Instances to get correct panel from Client uniqueId 
	 */
	public static final Map<Client, ExampleControlPanel> INSTANCES = new HashMap<Client, ExampleControlPanel>();
	
	private JTextField txtQuestion;
	private JTextPane txtMessage;
	private JTextPane txtAnswer;
	
	/**
	 * Initialize panel
	 */
	public ExampleControlPanel(Client client) {
		super(client);
		JLabel lblSendQuestion = new JLabel("Title:");
		
		txtQuestion = new JTextField();
		txtQuestion.setText("Question");
		txtQuestion.setColumns(10);
		
		JLabel lblMessage = new JLabel("Question:");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JButton btnAsk = new JButton("Ask");
		btnAsk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ask();
			}
		});
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JLabel lblAnswer = new JLabel("Answer:");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAsk, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblMessage)
								.addComponent(lblSendQuestion))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
								.addComponent(txtQuestion, GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblAnswer)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 379, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSendQuestion)
						.addComponent(txtQuestion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblMessage)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAsk)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAnswer))
					.addContainerGap(37, Short.MAX_VALUE))
		);
		
		txtAnswer = new JTextPane();
		scrollPane_1.setViewportView(txtAnswer);
		
		txtMessage = new JTextPane();
		txtMessage.setText("Do you know this is the example plugin?");
		scrollPane.setViewportView(txtMessage);
		setLayout(groupLayout);

	}
	
	/**
	 * Add question packet to queue
	 */
	public void ask() {
		try {
			super.getClient().addToSendQueue(new AskPacket(super.getClient(), txtQuestion.getText(), txtMessage.getText(), 30 /* Default seconds before close */));
		} catch (Exception ex) {
			ex.printStackTrace();
			ExampleClientPlugin.log("Failed to send packet: " + ex.getMessage());
		}
	}
	
	/**
	 * Put Clients uniqueId along with this panel in the map
	 */
	@Override
	public void onLoad() {
		INSTANCES.put(super.getClient(), this);
	}

	/**
	 * Remove this panel from the map
	 */
	@Override
	public void onClose() {
		INSTANCES.remove(super.getClient());
	}

	public JTextPane getAnswerTextField() {
		return txtAnswer;
	}
}
