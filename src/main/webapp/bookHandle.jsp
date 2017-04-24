<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Book Management</title>
<link rel="stylesheet"
	href='<spring:url value="/resources/bootstrap/css/bootstrap.css" />'>
<link rel="stylesheet"
	href='<spring:url value="/resources/custom/css/sticky-footer.css" />'>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/formvalidation/0.6.1/css/formValidation.css">
<link rel="stylesheet"
	href='<spring:url value="/resources/custom/css/custom.css" />'>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
      <![endif]-->
</head>
<body ng-app="app" ng-controller="controller" ng-init="getAllBooks()">
	<div class="container">

		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<form id="logoutForm" method="POST" action="${contextPath}/logout">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>

			<h2>
				Welcome ${pageContext.request.userPrincipal.name} | <a
					onclick="document.forms['logoutForm'].submit()">Logout</a>
			</h2>

		</c:if>
		<div class="page-header">
			<h1>Book Management</h1>
		</div>
		<!-- LIST BOOKS -->
		<div ng-show="bookDetail" class="row">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Title</th>
						<th>Author</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="book in listOfBooks">
						<td>{{book.title}}</td>
						<td>{{book.author}}</td>
						<td><span class="glyphicon glyphicon-eye-open text-default"
							title="View Book Detail" ng-click="getBookDetail(book.id)"></span>
							<span class="glyphicon glyphicon-edit text-default"
							title="Edit book" ng-click="getBook(book.id)"></span> <span
							class="glyphicon glyphicon-remove-circle text-danger deleteBtn"
							title="Delete book" data-toggle="confirmation"
							ng-really-click="deleteBook(book.id);"></span></td>
					</tr>
				</tbody>
			</table>
			<div class="row">
				<button type="button" ng-click="createNewBook()"
					class="btn btn-primary">Add new Book</button>

			</div>
		</div>
		<!-- /LIST BOOKS -->

		<!-- ADD BOOK MODAL -->
		<div class="modal fade" id="addingBookModal" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Add new Book</h4>
					</div>
					<div class="modal-body">
						<form name="newBookForm">
							<div class="form-group"
								ng-class="{ 'has-error': newBookForm.title.$touched && newBookForm.title.$invalid }">
								<label>Title</label> <input type="text" name="title"
									class="form-control" ng-model="newBook.title" required>

								<div class="help-block" ng-messages="newBookForm.title.$error"
									ng-if="newBookForm.title.$touched">
									<p ng-message="required">Title is required.</p>
								</div>
							</div>

							<div class="form-group"
								ng-class="{ 'has-error': newBookForm.author.$touched && newBookForm.author.$invalid }">
								<label>Author</label> <input type="text" name="author"
									class="form-control" ng-model="newBook.author" required>

								<div class="help-block" ng-messages="newBookForm.author.$error"
									ng-if="newBookForm.author.$touched">
									<p ng-message="required">Author is required.</p>
								</div>
							</div>

							<div class="form-group">
								<label>Description</label> <input type="text" name="description"
									class="form-control" ng-model="newBook.description">
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary"
							ng-disabled="newBookForm.$invalid" ng-click="addBook();">Add
							New Book</button>
						<button type="reset" class="btn btn-danger" data-dismiss="modal"
							ng-click="cancelAdding();">Cancel</button>
					</div>
				</div>
			</div>
		</div>


		<div class="modal fade" id="editBookModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Add new Book</h4>
					</div>
					<div class="modal-body">
						<form name="editBookForm">
							<div class="form-group"
								ng-class="{ 'has-error': editBookForm.title.$touched && editBookForm.title.$invalid }">
								<label>Title</label> <input type="text" name="title"
									class="form-control" ng-model="selectedBook.title" required>

								<div class="help-block" ng-messages="editBookForm.title.$error"
									ng-if="editBookForm.title.$touched">
									<p ng-message="required">Title is required.</p>
								</div>
							</div>

							<div class="form-group"
								ng-class="{ 'has-error': editBookForm.author.$touched && editBookForm.author.$invalid }">
								<label>Author</label> <input type="text" name="author"
									class="form-control" ng-model="selectedBook.author" required>

								<div class="help-block" ng-messages="editBookForm.author.$error"
									ng-if="editBookForm.author.$touched">
									<p ng-message="required">Author is required.</p>
								</div>
							</div>

							<div class="form-group">
								<label>Description</label> <input type="text" name="description"
									class="form-control" ng-model="selectedBook.description">
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary"
							ng-disabled="editBookForm.$invalid" ng-click="updateBook()">Update</button>
						<button type="reset" class="btn btn-danger"
							ng-click="cancelUpdating();">Cancel</button>
					</div>
				</div>
			</div>
		</div>

		<div ng-show="!bookDetail">
			<h1>Book Detail</h1>
			<div class="row">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Title</th>
							<th>Author</th>
							<th>Create By</th>
							<th>Date Create</th>
							<th>Update By</th>
							<th>Date Update</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>{{selectedBook.title}}</td>
							<td>{{selectedBook.author}}</td>
							<td>{{selectedBook.createBy}}</td>
							<td>{{selectedBook.dateCreate}}</td>
							<td>{{selectedBook.updateBy}}</td>
							<td>{{selectedBook.dateUpdate}}</td>
						</tr>
					</tbody>
				</table>
			</div>

			<button type="button" class="btn btn-info" ng-click=goBack()>Back</button>

		</div>

		<script type="text/javascript"
			src='<spring:url value="/resources/jquery/jquery.js" />'></script>
		<script type="text/javascript"
			src='<spring:url value="/resources/bootstrap/js/bootstrap.js" />'></script>
		<script type="text/javascript"
			src='<spring:url value="/resources/bootstrap-confirmation/bootstrap-confirmation.js" />'></script>
		<script type="text/javascript"
			src='<spring:url value="/resources/angularjs/angular.js" />'></script>
		<script type="text/javascript"
			src='<spring:url value="/resources/custom/js/index.js" />'></script>
</body>
</html>