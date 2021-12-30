package edu.bk;

import java.util.ArrayList;

public class LinearConflict {
	private ArrayList<Matrix> open = new ArrayList<Matrix>();
    private ArrayList<String> closematrixs = new ArrayList<String>();
    private String [] path;
    private String[] arraySolution;
    
    public void Solve(Matrix a){
        open.clear();
        closematrixs.clear();
        // Do sau cua trang thai bat dau = 0
        open.add(a);
        
        // Compute threshold
        int threshold = a.getAstar();
        
        System.out.println("threshold" + threshold);
        
        closematrixs.add(a.getClosedMatrix());
        System.out.println(a.getClosedMatrix());
        while (open.size() > 0){
            Matrix aa = open.get(0);
            open.remove(0);
            
            
            if(aa.getMtdistance() == 0){
            	getSolution(aa);
                break;
            }
            
            
            if(aa.moveBot() == true){
                Matrix bot = new Matrix();
                bot.isParentMoveBot(aa);
                if(bot.getMtdistance() == 0) {
                	getSolution(bot);
                	break;
                }
                if(checkclosed(bot.getClosedMatrix()) == true){
                    insertMatrix(bot);
                    closematrixs.add(bot.getClosedMatrix());
                    //System.out.println(bot.getClosedMatrix());
                }
            }
            if(aa.moveTop() == true){
                Matrix top = new Matrix();
                top.isParentMoveTop(aa);
                if(top.getMtdistance() == 0) {
                	getSolution(top);
                	break;
                }
                if(checkclosed(top.getClosedMatrix()) == true){
                    insertMatrix(top);
                    closematrixs.add(top.getClosedMatrix());
                    //System.out.println(top.getClosedMatrix());
                }
            }
            if(aa.moveLeft() == true){
                Matrix left = new Matrix();
                left.isParentMoveLeft(aa);
                if(left.getMtdistance() == 0) {
                	getSolution(left);
                	break;
                }
                if(checkclosed(left.getClosedMatrix()) == true){
                    insertMatrix(left);
                    closematrixs.add(left.getClosedMatrix());
                    //System.out.println(left.getClosedMatrix());
                }
            }
            if(aa.moveRight() == true){
                Matrix right = new Matrix();
                right.isParentMoveRight(aa);
                if(right.getMtdistance() == 0) {
                	getSolution(right);
                	break;
                }
                if(checkclosed(right.getClosedMatrix()) == true){
                    insertMatrix(right);
                    closematrixs.add(right.getClosedMatrix());
                    //System.out.println(right.getClosedMatrix());
                }
            }
        }
    }
    
    // Tinh heuristic linear conflict
    // 2 o vuong duoc coi la linear conflict neu chung cung nam tren 1 hang, o chinh xac cua chung cung nam tren 1 hang. Tuy nhien 2 o dang
    // nam nguoc chieu nhau
    
    // linear conflict heuristic = mahattan distance + 2 * total conflicts
    
    // compute conflict return total conflicts
    public int computeLinearConflict(Matrix mt) {
    	int totalConflicts = 0;
    	
    	int size = mt.getSizebox();
    	
    	for(int i = 0 ; i < size ; i++) {
    		ArrayList<Integer> rowData = new ArrayList<Integer>();
    		for(int j = 1 ; j <= size ; j++) {    			
    			// rowData luu thong tin cua 1 hang.
    			rowData.add(mt.getValue()[i][j]);
    		}
    		// Neu hang i co cac so i * size + 1 --> i * size + size
    		int start = i * size + 1;
    		for(int u = start ; u < start + size ; u++) {
    			if(rowData.contains(u)) {
	    			for(int v = u + 1 ; v <= start + size ; v++) {    				
	    				if(rowData.contains(v)) {
	    					if(rowData.indexOf(u) > rowData.indexOf(v)) {
	    						totalConflicts++;
	    					}
	    				}
	    			}
    			}
    		}
    	}
    	
    	for(int j = 1 ; j <= size ; j++) {
    		ArrayList<Integer> colData = new ArrayList<Integer>();
    		for(int i = 0 ; i < size ; i++) {
    			colData.add(mt.getValue()[i][j]);
    		}
    		
    		// Neu cot j co cac so 0 * size + j --> (size-1)*size + j
    		int start = j;
    		int end = (size - 1) * size + j;
    		
    		for(int u = start ; u < end ; u++) {
    			if(colData.contains(u)) {
    				for(int v = u+1 ; v <= end ; v++) {
    					if(colData.contains(v)) {
    						if(colData.indexOf(u) > colData.indexOf(v)) {
    							totalConflicts++;
    						}
    					}
    				}
    			}
    		}
    	}
    	
    	return totalConflicts;
    }
    
    // Xu ly khi mt la trang thai cuoi cung de xu ly truy vet
    private void getSolution(Matrix mt) {
    	
		path = mt.getMoveDirection().split("-");
        System.out.println(mt.getMoveDirection());
        System.out.println("parent String" + mt.getParentString());
        arraySolution = mt.getParentString().replace("25", " ").split("[-]");
        for(int i = 0 ; i < arraySolution.length ; i++) {
        	System.out.println(arraySolution[i]);
        }
        	
        for(int i = 0; i < path.length; i++){
            System.out.println(path[i]);
        }
    	
    }
    
    
    public void insertMatrix(Matrix b){
        int index = 0;
        for(int i = 0; i < open.size(); i++){
            if(b.getAstar() + computeLinearConflict(b) > open.get(i).getAstar() + computeLinearConflict(open.get(i))){
                index++;
            } else {
                break;
            }
        }
        open.add(index, b);
    }
    public boolean checkclosed(String closedmatrix){
        for(int i = 0; i < closematrixs.size(); i++){
            if(closedmatrix.equals(closematrixs.get(i))){
                return false;
            }
        }
        return true;
    }
    public String[] getArraySolution() {
        return arraySolution;
    }
	public ArrayList<Matrix> getOpen() {
		return open;
	}
	public void setOpen(ArrayList<Matrix> open) {
		this.open = open;
	}
	public ArrayList<String> getClosematrixs() {
		return closematrixs;
	}
	public void setClosematrixs(ArrayList<String> closematrixs) {
		this.closematrixs = closematrixs;
	}
	public void setArraySolution(String[] arraySolution) {
		this.arraySolution = arraySolution;
	}
	public String[] getPath() {
		return path;
	}
	public void setPath(String[] path) {
		this.path = path;
	}
}
