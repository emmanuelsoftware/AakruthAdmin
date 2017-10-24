
<!-- page content -->
<div class="right_col" role="main">
	<div class="">
		<div class="clearfix"></div>
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<div class="x_title">
						<h2>Sale Order Requisition</h2>
						<ul class="nav navbar-right panel_toolbox">
							<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
							<li><a class="close-link"><i class="fa fa-close"></i></a></li>
						</ul>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<table id="datatable-button"
							class="table table-striped table-bordered dt-responsive nowrap"
							width="100%">
							<thead>
								<tr>
									<th>Bill</th>
									<th>Customer</th>
									<th>Purchase Order</th>
									<th>Date of Order</th>
									<th>Assign To</th>
									<th></th>
								</tr>
							</thead>
						</table>
						<div class="btn-group btn-group-sm" role="group">
							<button class="btn btn-success" type="button" id="addButton">Add</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- /page content -->

<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog"
	aria-hidden="true" id="salesModal" data-keyboard="false"
	data-backdrop="static">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">

			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">Bill Detail</h4>
			</div>
			<div class="modal-body">
				<div class="container-fluid">
					<div class="x_content">
						<div class="" id="form">
							<form action="PurchaseServlet" method="get" id="purchaseForm">

								<div class="row">
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label" id="clilbl">Customer </label> <input
												class="form-control" type="text" required
												placeholder="Enter Mobile number / Name" autocomplete="on"
												name="search" id="search-box"> <select
												class="form-control" id="customerSelect"
												name="customerSelect">
											</select>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label" for="Date of sale" id="clilbl">Date
												of sale</label>
											<fieldset>
												<div class="control-group">
													<div class="controls">
														<div
															class="col-md-11 xdisplay_inputx form-group has-feedback">
															<input type="text" class="form-control has-feedback-left"
																id="single_cal1" placeholder="Date of sale"
																aria-describedby="inputSuccess2Status3"> <span
																class="fa fa-calendar-o form-control-feedback left"
																aria-hidden="true"></span> <span
																id="inputSuccess2Status3" class="sr-only">(success)</span>
														</div>
													</div>
												</div>
											</fieldset>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label" for="Purchase Order" id="clilbl">Purchase
												Order</label> <input class="form-control" type="text" required=""
												placeholder="Purchase Order" autocomplete="on" name="poNum"
												id="poNum">
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label" for="Tax" id="clilbl">Tax</label>
											<select class="form-control" id="taxSelect" name="taxSelect">
												<option value='S'>Inside State</option>
												<option value='C'>Outside State</option>
											</select>
										</div>
									</div>
								</div>
							</form>
						</div>

					</div>
					<div class="x_content">
						<table id="datatable-modal" class="table dt-responsive nowrap"
							width="100%">
							<thead>
								<tr>
									<th>Product</th>
									<th>Builder</th>
									<th>No of items</th>
									<th>Price</th>
									<th></th>
								</tr>
							</thead>
						</table>
						<div class="row">
							<div class="col-md-12">
								<div class="pull-right">
									<button class="btn btn-success btn-lg close" type="button"
										id="addSaleButton">
										<i class="fa fa-plus fa-2x"></i>
									</button>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-2">
								<label class="control-label" for="Tax" id="Total">Total:
								</label>
							</div>
							<div class="col-md-2">
								<label class="control-label" for="Tax" id="DiscountedAmount">Discounted
									Amount : </label>
							</div>
							<div class="col-md-2">
								<label class="control-label" for="Tax" id="CGST">CGST :
								</label>
							</div>
							<div class="col-md-2">
								<label class="control-label" for="Tax" id="SGST">SGST :
								</label>
							</div>
							<div class="col-md-2">
								<label class="control-label" for="Tax" id="IGST">IGST :
								</label>
							</div>
							<div class="col-md-2">
								<label class="control-label" for="Tax" id="GrandTotal">Grand
									Total : </label>
							</div>
						</div>
						<div class="row">
							<div class="col-md-2">
								<label class="control-label" for="Tax" id="TotalData"></label>
							</div>
							<div class="col-md-2">
								<label class="control-label" for="Tax" id="DiscountedAmountData"></label>
							</div>
							<div class="col-md-2">
								<label class="control-label" for="Tax" id="cgstData"></label>
							</div>
							<div class="col-md-2">
								<label class="control-label" for="Tax" id="sgstData"></label>
							</div>
							<div class="col-md-2">
								<label class="control-label" for="Tax" id="IgstData"></label>
							</div>
							<div class="col-md-2">
								<label class="control-label" for="Tax" id="GrandTotalData"></label>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<div class="pull-left">
					<form action="/Aakruth/bill/print">
						<input type="hidden" id="billId" name="billId" value="0">
						<button class="btn btn-success btn-lg close" type="submit">
							<i class="glyphicon glyphicon-download-alt fa-2x"></i>
						</button>
					</form>
				</div>
				<div class="btn-group btn-group-sm" role="group">
					<button type="button" class="btn btn-success" id="modalSave">Save</button>
					<button type="button" class="btn btn-primary" id="modalDelete">Delete</button>
					<button type="button" class="btn btn-default" id="modalClose">Close</button>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog"
	aria-hidden="true" id="saleAddModal">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">X</span>
				</button>
				<h4 class="modal-title" id="myModalLabel2">Sales Details</h4>
			</div>
			<div class="modal-body">
				<div class="container-fluid">
					<input type="hidden" name="billIdHidden" id="billIdHidden" />
					<div class="form-group">
						<label class="control-label" id="clilbl">Builder </label> <select
							class="form-control" id="builderSelect" name="builderSelect">
							<!-- Options load via JQUERY -->
							<option value=""></option>
						</select>
					</div>
					<div class="form-group">
						<label class="control-label" id="clilbl">Product </label> <input
							class="form-control" type="text" required
							placeholder="Enter Product Name" autocomplete="on"
							name="search" id="search-product"> <select
							class="form-control" id="productSelect" name="productSelect"
							disabled="disabled">
							<!-- Options load via JQUERY -->
						</select>
					</div>
					<div class="form-group">
						<label class="control-label" for="No of items" id="clilbl">No
							of items </label> <input class="form-control" type="number"
							required="required" placeholder="no of items" min="1"
							name="count" id="count">
					</div>
					<div class="form-group">
						<label class="control-label" for="No of items" id="clilbl">Price
						</label> <input class="form-control" type="number" required="required"
							placeholder="Price" min="1" name="price" id="price">
					</div>
					<div class="form-group">
						<label class="control-label" for="No of items" id="clilbl">Discount
						</label> <input class="form-control" type="number" required="required"
							placeholder="Discount" min="1" name="Discount" id="discount">
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<div class="btn-group btn-group-sm" role="group">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						id="saveButton">Save</button>
				</div>
			</div>

		</div>

	</div>
</div>


<!-- jQuery -->
<script src="static/vendors/jquery/dist/jquery.min.js"></script>
<script src="static/SaleOrder.js"></script>