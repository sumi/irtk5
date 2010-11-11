package com.irelaxa.irtk.client;

import com.google.gwt.user.client.Window;
import com.smartgwt.client.widgets.tree.TreeNode;
import com.smartgwt.client.widgets.tree.events.NodeClickEvent;
import com.smartgwt.client.widgets.tree.events.NodeClickHandler;

public class EmployeeTreeNode extends TreeNode implements NodeClickHandler{  
  
        public EmployeeTreeNode(String employeeId, String reportsTo, String name) {  
            setEmployeeId(employeeId);  
            setReportsTo(reportsTo);  
            setName(name);  
        }  
  
        public void setEmployeeId(String value) {  
            setAttribute("EmployeeId", value);  
        }  
  
        public void setReportsTo(String value) {  
            setAttribute("ReportsTo", value);  
        }  
  
        public void setName(String name) {  
            setAttribute("Name", name);  
        }  
        public void onNodeClick(NodeClickEvent e){
        	Window.alert("Node clicked = "+ e.getNode().getName());
        	
        	}
    }  
  