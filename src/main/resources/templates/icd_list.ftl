  
    
<div class="generic-container" >
   <div class="panel panel-success" ng-if="!ctrl.display">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="icdMeasure">List of ICD Measures </span> 
               <button type="button"   ng-click="ctrl.addICDMeasure()" ng-hide="ctrl.displayEditButton" class="btn btn-success  btn-xs custom-width floatRight"> Add </button>   
               <button type="button" ng-click="ctrl.editICDMeasure(ctrl.icdMeasureId)" ng-show="ctrl.displayEditButton" class="btn btn-primary btn-xs custom-width floatRight">Edit</button>  
              <button type="button" ng-click="ctrl.removeICDMeasure(ctrl.icdMeasureId)"  ng-show="ctrl.displayEditButton" class="btn btn-danger btn-xs custom-width floatRight">Remove</button>  
        </div>
        <div class="table-responsive">
			<div class="panel-body">
			
	        	<table datatable="" id="content"   dt-options="ctrl.dtOptions"  dt-columns="ctrl.dtColumns" dt-instance="ctrl.dtInstance" class="table-responsive table  bordered table-striped table-condensed datatable" cellspacing="0" width="100%"></table>
          </div>
		</div>
    </div>
     
    
    <div class="panel panel-success" ng-if="ctrl.display">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="icd">ICD Measure</span></div>
		<div class="panel-body">
	        <div class="formcontainer">
	            <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
	            <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
	            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
	                <input type="hidden" ng-model="ctrl.icdMeasure.id" />
	                
	                
	                   <div class="col-sm-12 ptInfo">
	                 
	                 
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="uname">Code</label>
	                        <div class="col-md-9">
	                            <input type="text" ng-model="ctrl.icdMeasure.code" name="code" class="username form-control input-sm" placeholder="Enter ICD Code" required ng-minlength="5"/>
	                             <div class="has-error" ng-show="myForm.$dirty">
	                                  <span ng-show="myForm.code.$error.required">This is a required field</span>
                                      <span ng-show="myForm.code.$error.minlength">Minimum length required is 5</span>
                                      <span ng-show="myForm.code.$invalid">This field is invalid </span>
                                  </div>
	                        </div>
	                    </div>
	                    
	                      <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="uname">Description</label>
	                        <div class="col-md-9">
	                            <input type="text" ng-model="ctrl.icdMeasure.description" name="description" class="username form-control input-sm" placeholder="Enter  Description" required ng-minlength="5"/>
	                             <div class="has-error" ng-show="myForm.$dirty">
	                                  <span ng-show="myForm.description.$error.required">This is a required field</span>
                                      <span ng-show="myForm.description.$error.minlength">Minimum length required is 5</span>
                                      <span ng-show="myForm.description.$invalid">This field is invalid </span>
                                  </div>
	                        </div>
	                    </div>
	               
	                  <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="uname">HCC</label>
	                        <div class="col-md-9">
	                            <input type="text" ng-model="ctrl.icdMeasure.hcc" name="hcc" class="username form-control input-sm" placeholder="Enter HCC code"  ng-minlength="1"/>
	                             <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.hcc.$error.minlength">Minimum length required is 1</span>
                                      <span ng-show="myForm.hcc.$invalid">This field is invalid </span>
                                  </div>
	                        </div>
	                    </div>
	                    
                      <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="uname">RxHCC</label>
	                        <div class="col-md-9">
	                            <input type="text" ng-model="ctrl.icdMeasure.rxhcc" name="rxhcc" class="username form-control input-sm" placeholder="Enter RxHCC code"  ng-minlength="1"/>
	                             <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.rxhcc.$error.minlength">Minimum length required is 1</span>
                                      <span ng-show="myForm.rxhcc.$invalid">This field is invalid </span>
                                  </div>
	                        </div>
	                    </div>
	                    
        </div>
        
         
          
	                <div class="row">
	                    <div class="form-actions floatRight">
	                        <input type="submit"  value="{{!ctrl.icdMeasure.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-xs" ng-disabled="myForm.$invalid || myForm.$pristine">
	                        <button type="button" ng-click="ctrl.cancelEdit()" class="btn btn-warning btn-xs"    >Cancel</button>
	                        <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-xs"  ng-if="ctrl.icdMeasure.id" ng-disabled="myForm.$pristine">Reset Form</button>
	                    </div>
	                </div>
	            </form>
    	    </div>
		</div>	
    </div>
    
    
</div>