package com.irelaxa.irtk.client;

import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.smartgwt.client.types.TreeModelType;  
import com.smartgwt.client.util.EventHandler;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.DrawEvent;  
import com.smartgwt.client.widgets.events.DrawHandler;  
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.tree.Tree;  
import com.smartgwt.client.widgets.tree.TreeGrid;  
import com.smartgwt.client.widgets.tree.TreeGridField;  
import com.smartgwt.client.widgets.tree.TreeNode;  
import com.smartgwt.client.widgets.tree.events.NodeClickEvent;
import com.smartgwt.client.widgets.tree.events.NodeClickHandler;
  
public class TreeTest extends Composite  {  
	private static TreeTest instance = null;
	EmployeeTreeNode node2;
    public  TreeTest() {  
        TreeGrid treeGrid = new TreeGrid();  
        treeGrid.setWidth(300);  
        treeGrid.setHeight(400);  
  
        TreeGridField field = new TreeGridField("Name", "Love Makes the World Go Round");  
        field.setCanSort(false);  
  
        treeGrid.setFields(field);  
  
        final Tree tree = new Tree();  
        tree.setModelType(TreeModelType.PARENT);  
        tree.setNameProperty("Name");  
        tree.setIdField("EmployeeId");  
        tree.setParentIdField("ReportsTo");  
        tree.setShowRoot(true);  
  
        EmployeeTreeNode root = new EmployeeTreeNode("4", "1", "Romance Goals");  
        node2 = new EmployeeTreeNode("5", "4", "Me");  
        EmployeeTreeNode node3 = new EmployeeTreeNode("6", "4", "My Friends"); 
        EmployeeTreeNode node4 = new EmployeeTreeNode("7", "4", "My Family");
        EmployeeTreeNode node5 = new EmployeeTreeNode("8", "4", "My Sponsorships");
        EmployeeTreeNode node6 = new EmployeeTreeNode("9", "1", "Romance Calender"); 
        EmployeeTreeNode node7 = new EmployeeTreeNode("10", "9", "Me");  
        EmployeeTreeNode node8 = new EmployeeTreeNode("11", "9", "My Friends"); 
        EmployeeTreeNode node9 = new EmployeeTreeNode("12", "9", "My Family");
        EmployeeTreeNode node10 = new EmployeeTreeNode("13", "9", "My Sponsorships");
        EmployeeTreeNode node11 = new EmployeeTreeNode("14", "1", "Romance $ Saving");
        EmployeeTreeNode node12 = new EmployeeTreeNode("15", "14", "Me");  
        EmployeeTreeNode node13 = new EmployeeTreeNode("16", "14", "My Friends"); 
        EmployeeTreeNode node14 = new EmployeeTreeNode("17", "14", "My Family");
        EmployeeTreeNode node15 = new EmployeeTreeNode("18", "14", "My Sponsorships");
        EmployeeTreeNode node16 = new EmployeeTreeNode("19", "1", "Romance Advice"); 
        EmployeeTreeNode node17 = new EmployeeTreeNode("20", "19", "Me");  
        EmployeeTreeNode node18 = new EmployeeTreeNode("21", "19", "My Friends"); 
        EmployeeTreeNode node19 = new EmployeeTreeNode("22", "19", "My Family");
        EmployeeTreeNode node20 = new EmployeeTreeNode("23", "19", "My Sponsorships");
        EmployeeTreeNode node21 = new EmployeeTreeNode("24", "1", "Romantic Activities"); 
        EmployeeTreeNode node22 = new EmployeeTreeNode("25", "24", "Me");  
        EmployeeTreeNode node23 = new EmployeeTreeNode("26", "24", "My Friends"); 
        EmployeeTreeNode node24 = new EmployeeTreeNode("27", "24", "My Family");
        EmployeeTreeNode node25 = new EmployeeTreeNode("28", "24", "My Sponsorships");
        EmployeeTreeNode node26 = new EmployeeTreeNode("29", "1", "Romantic Gifts"); 
        EmployeeTreeNode node27 = new EmployeeTreeNode("30", "29", "Me");  
        EmployeeTreeNode node28 = new EmployeeTreeNode("31", "29", "My Friends"); 
        EmployeeTreeNode node29 = new EmployeeTreeNode("32", "29", "My Family");
        EmployeeTreeNode node30 = new EmployeeTreeNode("33", "29", "My Sponsorships");
        tree.setData(new TreeNode[] { root, node2, node3, node4, node5, node6, node7, node8, node9, node10, node11,
        		node12, node13, node14, node15, node16, node17, node18, node19, node20, node21, node22, node23,
        		node24, node25, node26, node27, node28, node29, node30});
  
        treeGrid.addDrawHandler(new DrawHandler() {  
            public void onDraw(DrawEvent event) {  
                tree.openAll();  
            }  
        });  
        treeGrid.addCellClickHandler(new CellClickHandler() {
        	public void onCellClick(CellClickEvent event) {
        		
        	if(event.getRowNum() ==1){
        	
        }
        	}
        });
        treeGrid.setData(tree);  
  
       // treeGrid.draw();  
        initWidget(treeGrid);
    }  
  
    public static TreeTest getInstance() {
	     if(instance == null) {
	        instance = new TreeTest();
	     }
	     return instance;
	 }
    
    
    
    }
    
