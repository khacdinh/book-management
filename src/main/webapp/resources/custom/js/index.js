(function() {
  var app = angular.module("app", []);
  
  app.controller("controller", function($scope, $http) {
    $scope.listOfBooks = [];
    $scope.selectedBookId = undefined;
    $scope.selectedBookDetailId = undefined;
    $scope.selectedBook = {};
    $scope.newBook = {};
    $scope.book = {};
    $scope.bookDetail = true;

    function getAllBooks() {
      $http.get("api/v1/bookRest/books/").then(function(response) {
        $scope.listOfBooks = response.data;
        console.log($scope.listOfBooks);
      });
    }
    function  createNewBook() {
        $('#addingBookModal').modal('show');
      }
    
    function getBook(id) {
      $scope.selectedBookId = id;
      console.log($scope.selectedBookId);
      if ($scope.selectedBookId) {
        $http.get("api/v1/bookRest/findBookById/" + $scope.selectedBookId).then(function(response) {
          $scope.selectedBook = response.data;
        });
      }
      $('#editBookModal').modal('show');
    }
    
    function getBookDetail(id) {
        $scope.selectedBookId = id;
        $scope.bookDetail = false;
        console.log($scope.selectedBookId);
        if ($scope.selectedBookId) {
          $http.get("api/v1/bookRest/viewBookDetail/" + $scope.selectedBookId).then(function(response) {
            $scope.selectedBook = response.data;
          });
        }
      }
    

    function updateBook() {
      $http.put("api/v1/bookRest/updateBook/" + $scope.selectedBookId, $scope.selectedBook).then(function(response) {
        console.log(response.data);
        if (response.data === 'SUCCESS') {
          getAllBooks();
        }
      });
      this.cancelUpdating();

    }
    
    function cancelUpdating() {
      $scope.selectedBookId = undefined;
      $scope.selectedBook = null;
      $('#editBookModal').modal('hide');

    }
    
    function addBook() {
      $http.post("api/v1/bookRest/book/", $scope.newBook).then(function(response) {
        console.log($scope.newBook);
        console.log(response.data);
        if (response.data === 'SUCCESS') {
          getAllBooks();
        }
      });
      $scope.newBook = null;
    }

    function cancelAdding() {
      $scope.newBook = {};
    }
    
    function deleteBook(id) {
      $http.delete("api/v1/bookRest/delbook/" + id).then(function(response) {
        console.log("Result code: ", response.data);
        if (response.data === 'SUCCESS') {
          getAllBooks();
          if (id == $scope.selectedBookId) {
            $scope.selectedBookId = undefined;
            $scope.selectedBook = null;
          }
        }
      });
    }

    function goBack(){
        $scope.bookDetail = true;
        getAllBooks();
    }
    
    $scope.createNewBook=createNewBook;
    $scope.getAllBooks = getAllBooks;
    $scope.getBook = getBook;
    $scope.updateBook = updateBook;
    $scope.cancelUpdating = cancelUpdating;
    $scope.addBook = addBook;
    $scope.cancelAdding = cancelAdding;
    $scope.deleteBook = deleteBook;
    $scope.getBookDetail=getBookDetail;
    $scope.goBack=goBack;
  });
  
  app.directive('ngReallyClick', [ function() {
    return {
      restrict : 'A',
      link : function(scope, element, attrs) {
        element.confirmation({
          onConfirm: function() {
            scope.$apply(attrs.ngReallyClick);
          }
        });
      }
    }
  } ]);
})();