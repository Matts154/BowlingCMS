/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var app = angular.module('BowlingCMS', ['ngRoute']);

app.config(["$routeProvider", "$locationProvider", function ($routeProvider, $locationProvider) {
        $routeProvider
                .when('/', {
                    templateUrl: 'root.html'
                })
                .when('/events', {
                    templateUrl: 'event_viewer.html',
                    controller: 'EventsController'
                })
                .when('/people', {
                    templateUrl: 'person_viewer.html',
                    controller: 'PersonController'
                })
                .when('/viewer', {
                    templateUrl: 'viewer.html',
                    controller: 'ViewerController'
                })
                .when('/settings', {
                    templateUrl: 'settings.html',
                    controller: 'SettingsController'
                })
                .otherwise({
                    templateUrl: 'error_404.html'
                });
    }
]);

//app.config(["$provide", function ($provide) {
//        $provide.decorator("$exceptionHandler", function ($delegate, $injector) {
//            return function (exception, cause) {
//                var $rootScope = $injector.get("$rootScope");
//                $rootScope.addExceptionAlert({message: "Exception", reason: exception}); // This represents a custom method that exists within $rootScope
//                $delegate(exception, cause);
//            };
//        });
//    }
//]);

app.controller('MainController', ['$rootScope', '$scope', '$http', '$log', function ($rootScope, $scope, $http, $log) {
        // Initialize events and people
        // Get events
        $http.get('http://localhost:8080/events')
                .then(function successCallback(response) {
                    $rootScope.events = response.data._embedded.events;
                }, function errorCallback(response) {
                    $log.error(response);
                });

        // Initializing the dropdown with people. May have mustache.js do this instead to improve percieved performance.
        $http.get('http://localhost:8080/persons')
                .then(function successCallback(response) {
                    $rootScope.people = response.data._embedded.persons;
                }, function errorCallBack(response) {
                    $log.error(response);
                });

        // Watchers for nav

        // JS Objects for viewer controller config
        var eventConfig = {
            processScores: function (s) {
                var event = _.find($scope.events, function (e) {
                    return e._links.self.href === href;
                });
                s.eventName = event.name;
                s.date = event.eventDate;
                s.timestamp = Date.parse(s.date);
                s.avg = Math.floor((parseInt(s.game1) + parseInt(s.game2) + parseInt(s.game3)) / 3);
            },
            dropdown: {
                items: $rootScope.events,
                label: "item.eventName",
                href: "item._lins.event.href"
            },
            table: {
                headers: ["Bowler", "Date", "Game #1", "Game #2", "Game #3"],
                keys: ["bowler", "date", "game1", "game2", "game3"]
            },
            c3: {
                bindto: '#chart',
                data: {
                    json: $scope.scores,
                    keys: {
                        value: ['game1', 'game2', 'game3']
                    },
                    names: {
                        game1: 'Game #1',
                        game2: 'Game #2',
                        game3: 'Game #3'
                    },
                    type: 'bar'
                },
                axis: {
                    x: {
                        type: 'category',
                        categories: _.map($scope.scores, function (series) {
                            var person = $scope.findPerson(series._links.person.href);
                            return person.firstName + " " + person.lastName;
                        })
                    }
                },
                legend: {
                    show: false
                }
            }
        };
        $rootScope.config = eventConfig;
    }
]);

app.controller('ViewerController', ['$rootScope', '$scope', '$http', '$log', function ($rootScope, $scope, $http, $log) {
        $scope.selectedItem = null;
        $scope.items = $rootScope.config.dropdown.items;
        $scope.dropdown.href = $rootScope.config.dropdown.href;
        $scope.dropdown.label = $rootScope.config.dropdown.label;
        $scope.scores = {};

        // When the dropdown selection is changed, repopulate table with bowling scores
        $scope.$watch('selectedItem', function (newVal, oldVal) {
            if (newVal == null) {
                $scope.scores = {};
                return;
            }

            $http.get($scope.selectedItem)	// Get the event details
                    .then(function successCallback(response) {
                        $scope.scores = _.each(response.data.scores, $rootScope.processScores);
                        c3.generate($rootScope.config.c3);
                    }, function errorCallback(response) {
                        $log.error(response);
                    });
        });
    }

]);

app.controller('EventsController', ['$scope', '$http', '$log', function ($scope, $http, $log) {
        $scope.selectedEvent = null;
        $scope.events = [];
        $scope.scores = {};
        $scope.person = {};


        // When the dropdown selection is changed, repopulate table with bowling scores
        $scope.$watch('selectedEvent', function (newVal, oldVal) {
            if (newVal == null) {
                $scope.scores = {};
                return;
            }

            $http.get($scope.selectedEvent)	// Get the event details
                    .then(function successCallback(response) {
                        $scope.scores = response.data.scores;
                        chart = c3.generate({
                            bindto: '#chart',
                            data: {
                                json: $scope.scores,
                                keys: {
                                    value: ['game1', 'game2', 'game3']
                                },
                                names: {
                                    game1: 'Game #1',
                                    game2: 'Game #2',
                                    game3: 'Game #3',
                                },
                                type: 'bar'
                            },
                            axis: {
                                x: {
                                    type: 'category',
                                    categories: _.map($scope.scores, function (series) {
                                        var person = $scope.findPerson(series._links.person.href);
                                        return person.firstName + " " + person.lastName;
                                    })
                                }
                            },
                            legend: {
                                show: false
                            }
                        });
                    }, function errorCallback(response) {
                        $log.error(response);
                    });
        });

        // Initializing the dropdown with events. May have mustache.js do this instead to improve percieved performance.
        $http.get('http://localhost:8080/events')
                .then(function successCallback(response) {
                    $scope.events = response.data._embedded.events;
                }, function errorCallback(response) {
                    $log.error(response);
                });

        // Get persons
        $http.get('http://localhost:8080/persons')
                .then(function successCallback(response) {
                    $scope.person = response.data._embedded.persons;
                }, function errorCallBack(response) {
                    $log.error(response);
                });

        $scope.findPerson = function (href) {
            href = href.split("/");
            var index = parseInt(href[href.length - 1]) - 1;
            return $scope.person[index];
        };
    }
]);

app.controller('PersonController', ['$scope', '$http', '$log', function ($scope, $http, $log) {
        $scope.selectedPerson = null;
        $scope.events = [];
        $scope.scores = {};
        $scope.person = {};

        // When the dropdown selection is changed, repopulate table with bowling scores
        $scope.$watch('selectedPerson', function (newVal, oldVal) {
            if (newVal == null) {
                $scope.scores = {};
                return;
            }

            $http.get($scope.selectedPerson)	// Get the event details
                    .then(function successCallback(response) {
                        $scope.scores = _.each(response.data.scores, function (s) {
                            var event = $scope.findEvent(s._links.event.href)
                            s.eventName = event.name;
                            s.date = event.eventDate;
                            s.timestamp = Date.parse(s.date);
                            s.avg = Math.floor((parseInt(s.game1) + parseInt(s.game2) + parseInt(s.game3)) / 3);
                        });
                        c3.generate({
                            bindto: '#chart',
                            data: {
                                x: 'date',
                                json: $scope.scores,
                                keys: {
                                    value: ['game1', 'game2', 'game3', 'date', 'avg']
                                },
                                names: {
                                    game1: 'Game #1',
                                    game2: 'Game #2',
                                    game3: 'Game #3',
                                    avg: 'Average',
                                    date: 'Date'
                                },
                                types: {
                                    game1: 'scatter',
                                    game2: 'scatter',
                                    game3: 'scatter',
                                    avg: 'line'
                                }
                            },
                            axis: {
                                x: {
                                    type: 'timeseries',
                                    tick: {
                                        format: '%m/%d/%y',
                                        fit: true
                                    },
                                    show: false
                                }
                            },
                            legend: {
                                show: false
                            },
                            tooltip: {
                                format: {
                                    title: function (date) {
                                        var index = _.findIndex($scope.scores, {date: date.toISOString().split("T")[0]});
                                        return $scope.scores ? $scope.findEvent($scope.scores[index]._links.event.href).name : null;
                                    }
                                },
                                grouped: true
                            },
                            subchart: {
                                show: true
                            },
                            zoom: {
                                enabled: true
                            }
                        });
                    }, function errorCallback(response) {
                        $log.error(response);
                    });
        });

        // Get events
        $http.get('http://localhost:8080/events')
                .then(function successCallback(response) {
                    $scope.events = response.data._embedded.events;
                }, function errorCallback(response) {
                    $log.error(response);
                });

        // Initializing the dropdown with people. May have mustache.js do this instead to improve percieved performance.
        $http.get('http://localhost:8080/persons')
                .then(function successCallback(response) {
                    $scope.person = response.data._embedded.persons;
                }, function errorCallBack(response) {
                    $log.error(response);
                });

        $scope.findEvent = function (href) {
            if (!href) {
                return null;
            }
            return _.find($scope.events, function (e) {
                return e._links.self.href === href;
            });
        };
    }
]);

app.controller('SettingsController', ['$rootScope', '$scope', '$http', '$log', function ($rootScope, $scope, $http, $log) {

    }
]);