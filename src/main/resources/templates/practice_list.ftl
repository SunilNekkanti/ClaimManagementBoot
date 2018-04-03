  
    
<div class="generic-container" >
<div class="panel panel-success" ng-if="!ctrl.display">
<!-- Default panel contents -->
<div class="panel-heading"><span class="practice">List of Practice </span> 
<button type="button"   ng-click="ctrl.addPractice()" ng-hide="ctrl.displayEditButton" class="btn btn-success  btn-xs custom-width floatRight"> Add </button>   
<button type="button" ng-click="ctrl.editPractice(ctrl.practiceId)" ng-show="ctrl.displayEditButton" class="btn btn-primary btn-xs custom-width floatRight">Edit</button>  
<button type="button" ng-click="ctrl.removePractice(ctrl.practiceId)"  ng-show="ctrl.displayEditButton" class="btn btn-danger btn-xs custom-width floatRight">Remove</button>  
</div>
<div class="table-responsive">
<div class="panel-body">
<table datatable="" id="content"   dt-options="ctrl.dtOptions"  dt-columns="ctrl.dtColumns" dt-instance="ctrl.dtInstance" class="table-responsive table  bordered table-striped table-condensed datatable" cellspacing="0" width="100%"></table>
</div>
</div>
</div>
     
<div class="panel panel-success" ng-if="ctrl.display">
<!-- Default panel contents -->
<div class="panel-heading"><span class="practice">Practice</span></div>
<div class="panel-body">
<div class="formcontainer">
<div class="alert alert-success" practice="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
<div class="alert alert-danger" practice="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
<form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
<input type="hidden" ng-model="ctrl.practice.id" />
	                
<div class="row">
<div class="form-group col-md-12">
<label class="col-md-1 col-md-offset-4 control-lable" for="uname">Short Name</label>
<div class="col-md-3">
<input type="text" ng-model="ctrl.practice.shortName" name="shortName" class="username form-control input-sm" placeholder="Enter Short Name" required ng-minlength="3"/>
<div class="has-error" ng-show="myForm.$dirty">
	                                 
</div>
</div>
</div>
	                    
<div class="form-group col-md-12">
<label class="col-md-1 col-md-offset-4 control-lable" for="uname">Name</label>
<div class="col-md-3">
<!--   <select class=" form-control" ng-model="ctrl.practice.name" name="name" ng-options="practice.name for shortName in ctrl.practices | orderBy:'name' track by practice.shortName" required>   -->
<input type="text" ng-model="ctrl.practice.name" name="name" class="username form-control input-sm" placeholder="Enter Name" required ng-minlength="5"/> 
<div class="has-error" ng-show="myForm.$dirty">
	                                  
</div>
</div>
</div>
	               
<div class="form-group col-md-12">
<label class="col-md-1 col-md-offset-4 control-lable" for="uname">NPI</label>
<div class="col-md-3">
<input type="text" ng-model="ctrl.practice.npi" name="npi" class="username form-control input-sm" placeholder="Enter  NPI" required ng-minlength="5"/>
<div class="has-error" ng-show="myForm.$dirty">
</div>
</div>
</div>
	               
<div class="form-group col-md-12">
<label class="col-md-1 col-md-offset-4 control-lable" for="uname">Tax ID</label>
<div class="col-md-3">
<input type="text" ng-model="ctrl.practice.taxId" name="taxId" class="username form-control input-sm" placeholder="Enter Tax ID" required ng-minlength="5"/>
<div class="has-error" ng-show="myForm.$dirty">
<span ng-show="myForm.description.$error.required">This is a required field</span>
<span ng-show="myForm.description.$error.minlength">Minimum length required is 5</span>
<span ng-show="myForm.description.$invalid">This field is invalid </span>
</div>
</div>
</div>
	               
<div class="row">
<div class="form-group col-md-12">
<label class="col-md-1 col-md-offset-4 control-lable" for="uname">Clearing House URL</label>
<div class="col-md-3">
<input type="text" ng-model="ctrl.practice.clearingHouseURL" name="clearingHouseURL" class="username form-control input-sm" placeholder="Enter Clearing House URL" required ng-minlength="3"/>
<div class="has-error" ng-show="myForm.$dirty">
	                                 
</div>
</div>
</div>
	                    
<div class="row">
<div class="form-group col-md-12">
<label class="col-md-1 col-md-offset-4 control-lable" for="uname">User Name</label>
<div class="col-md-3">
<input type="text" ng-model="ctrl.practice.userName" name="userName" class="username form-control input-sm" placeholder="Enter User Name" required ng-minlength="3"/>
<div class="has-error" ng-show="myForm.$dirty">
	                                 
</div>
</div>
</div>
	                    
<div class="row">
<div class="form-group col-md-12">
<label class="col-md-1 col-md-offset-4 control-lable" for="uname">Password</label>
<div class="col-md-3">
<input type="text" ng-model="ctrl.practice.password" name="password" class="username form-control input-sm" placeholder="Enter Password" required ng-minlength="3"/>
<div class="has-error" ng-show="myForm.$dirty">
</div>
</div>
</div>
</div>
        
<div class="row">
<div class="form-actions floatRight col-md-offset-8">
<input type="submit"  value="{{!ctrl.practice.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-xs" ng-disabled="myForm.$invalid || myForm.$pristine">
<button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-xs" ng-show="!ctrl.practice.id" ng-disabled="myForm.$pristine">Reset Form</button>
<button type="button" ng-click="ctrl.cancelEdit()" class="btn btn-warning btn-xs">Cancel</button>
<button type="button" ng-click="ctrl.removePractice(ctrl.practice.id)" class="btn btn-danger btn-xs" ng-show="ctrl.practice.id" >Delete</button>
</div>
</div>
</form>
</div>
</div>	
</div>
</div>