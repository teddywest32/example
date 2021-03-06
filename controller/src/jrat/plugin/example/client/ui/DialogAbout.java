package jrat.plugin.example.client.ui;


import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;


@SuppressWarnings("serial")
public class DialogAbout extends JDialog {

	/**
	 * Initialize about dialog
	 */
	public DialogAbout() {
		setTitle("About");
		setResizable(false);
		setBounds(100, 100, 381, 213);
				
		JLabel lblName = new JLabel("Name: Example Plugin");
		
		JLabel lblVersion = new JLabel("Version: 1.0");
		
		JLabel lblAuthorShow = new JLabel("Author: ");
		
		JLabel lblAuthor = new JLabel("jRAT");
		lblAuthor.setForeground(Color.BLUE);
		
		JLabel lblDescription = new JLabel("Description:");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblName)
						.addComponent(lblVersion)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblAuthorShow)
							.addGap(6)
							.addComponent(lblAuthor))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDescription)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 293, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(11, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblName)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblVersion)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAuthorShow)
						.addComponent(lblAuthor))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDescription)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JTextPane textPane = new JTextPane();
		textPane.setText("jRAT Example Plugin");
		scrollPane.setViewportView(textPane);
		getContentPane().setLayout(groupLayout);

	}
}
