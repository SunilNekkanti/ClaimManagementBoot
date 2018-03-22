  
    
<div class="generic-container" >
   <div class="panel panel-success" ng-if="!ctrl.display">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="weightage">List of Weightage </span> 
               <button type="button"   ng-click="ctrl.addWeightage()" ng-hide="ctrl.displayEditButton" class="btn btn-success  btn-xs custom-width floatRight"> Add </button>   
               <button type="button" ng-click="ctrl.editWeightage(ctrl.weightageId)" ng-show="ctrl.displayEditButton" class="btn btn-primary btn-xs custom-width floatRight">Edit</button>  
              <button type="button" ng-click="ctrl.removeWeightage(ctrl.weightageId)"  ng-show="ctrl.displayEditButton" class="btn btn-danger btn-xs custom-width floatRight">Remove</button>  
        </div>
        <div class="table-responsive">
			<div class="panel-body">
			
	        	<table datatable="" id="content"   dt-options="ctrl.dtOptions"  dt-columns="ctrl.dtColumns" dt-instance="ctrl.dtInstance" class="table-responsive table  bordered table-striped table-condensed datatable" cellspacing="0" width="100%"></table>
          </div>
		</div>
    </div>
     
    
    <div class="panel panel-success" ng-if="ctrl.display">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="cpt">Weightage</span></div>
		<div class="panel-body">
	        <div class="formcontainer">
	            <div class="alert alert-success" weightage="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
	            <div class="alert alert-danger" weightage="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
	            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
	                <input type="hidden" ng-model="ctrl.weightage.id" />
	                
	                
	                      
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-1 col-md-offset-4 control-lable" for="uname">Condition</label>
	                        <div class="col-md-3">
	                            <input type="text" ng-model="ctrl.weightage.conditionExp" name="conditionExp" class="username form-control input-sm" placeholder="Enter value" required ng-minlength="4" />
	                        </div>
	                    </div>
	                </div>
	                    
	                  
	                    <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-1 col-md-offset-4 control-lable" for="uname">Percentage</label>
	                        <div class="col-md-3">
	                            <input type="text" ng-model="ctrl.weightage.percentage" name="percentage" class="username form-control input-sm" placeholder="Enter Percentage" required ng-minlength="1"/>
	                        </div>
	                    </div>
	                </div>

	                  
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-1 col-md-offset-4 control-lable" for="uname">Weightage</label>
	                        <div class="col-md-3">
	                        <select class=" form-control" ng-model="ctrl.weightage.wtageType" name="wtageType" ng-options="weightageType.description for weightageType in ctrl.weightageTypes | orderBy:'description' track by weightageType.description" required>
	                          
	                           <!-- <input type="text" ng-model="ctrl.weightage.wtageType.description" name="description" class="username form-control input-sm" placeholder="Enter WeightageType" required ng-minlength="4" />  -->
	                        </div>
	                    </div>
	                </div>
	                    
           
        </div>
        
         
          
	                <div class="row">
	                    <div class="form-actions floatRight">
	                        <input type="submit"  value="{{!ctrl.weightage.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-xs" ng-disabled="myForm.$invalid || myForm.$pristine">
	                        <button type="button" ng-click="ctrl.cancelEdit()" class="btn btn-warning btn-xs" ng-show="ctrl.claimStatusDetail.id" >Cancel</button>
	                        <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-xs"  ng-show="!ctrl.weightage.id" ng-disabled="myForm.$pristine">Reset Form</button>
	                        <button type="button" ng-click="ctrl.removeWeightage(ctrl.weightage.id)" class="btn btn-warning btn-sm" ng-show="ctrl.weightage.id" >Delete</button>
	                    </div>
	                </div>
	            </form>
    	    </div>
		</div>	
    </div>
    
    
</div>