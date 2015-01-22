// Generated by CoffeeScript 1.6.3
(function() {
  (function($) {
    var alignTable, endIndexForPage, getRows, numPages, onPageNav, paginationItem, paginationRange, paint, paintPagination, paintTableRows, realMod, startIndexForPage, tableNav,
      _this = this;
    tableNav = function(opts) {
      return $.each($(this.selector), function(idx, table) {
        var options, _ref, _ref1, _ref2, _ref3, _ref4, _ref5, _ref6, _ref7;
        options = {};
        options.table = $(table);
        options.childSelector = (_ref = opts.childSelector) != null ? _ref : 'tr';
        options.paginationSelector = (_ref1 = opts.pagination) != null ? _ref1 : options.table.siblings('.pagination');
        options.itemsPerPage = (_ref2 = opts.itemsPerPage) != null ? _ref2 : 10;
        options.hideWhenOnePage = (_ref3 = opts.hideWhenOnePage) != null ? _ref3 : true;
        options.currentPage = (_ref4 = opts.initialPage) != null ? _ref4 : 0;
        options.alignLastPage = (_ref5 = opts.alignLastPage) != null ? _ref5 : true;
        options.paginationSize = (_ref6 = opts.paginationSize) != null ? _ref6 : 5;
        options.showAdditionalControls = (_ref7 = opts.showAdditionalControls) != null ? _ref7 : false;
        if (numPages(options) > options.paginationSize) {
          options.showAdditionalControls = true;
        }
        $(options.paginationSelector).on('click', 'li', options, onPageNav);
        alignTable(options);
        return paint(options);
      });
    };
    startIndexForPage = function(options, page) {
      return options.itemsPerPage * page;
    };
    endIndexForPage = function(options, page) {
      return startIndexForPage(options, page + 1) - 1;
    };
    getRows = function(options) {
      return options.table.find(options.childSelector);
    };
    numPages = function(options) {
      return Math.ceil(getRows(options).length / options.itemsPerPage);
    };
    paint = function(options) {
      paintTableRows(options);
      return paintPagination(options);
    };
    onPageNav = function(ev) {
      ev.data.currentPage = parseInt($(ev.target).attr('data-page-num'));
      return paint(ev.data);
    };
    paintTableRows = function(options) {
      var endRow, startRow;
      startRow = startIndexForPage(options, options.currentPage);
      endRow = endIndexForPage(options, options.currentPage);
      options.table.find("" + options.childSelector + ":hidden").show();
      options.table.find("" + options.childSelector + ":lt(" + startRow + ")").hide();
      return options.table.find("" + options.childSelector + ":gt(" + endRow + ")").hide();
    };
    paintPagination = function(options) {
      var i, markup, num, pagination, _i, _len, _ref;
      num = numPages(options);
      pagination = $(options.paginationSelector);
      if (num === 1 && options.hideWhenOnePage) {
        return pagination.hide();
      }
      markup = [];
      if (options.showAdditionalControls) {
        if (num > options.paginationSize) {
          markup.push(paginationItem(options, 0, "&laquo;"));
        }
        markup.push(paginationItem(options, Math.max(0, options.currentPage - 1), "&lsaquo;"));
      }
      _ref = paginationRange(options, num);
      for (_i = 0, _len = _ref.length; _i < _len; _i++) {
        i = _ref[_i];
        markup.push(paginationItem(options, i, i + 1));
      }
      if (options.showAdditionalControls) {
        markup.push(paginationItem(options, Math.min(num - 1, options.currentPage + 1), "&rsaquo;"));
        if (num > options.paginationSize) {
          markup.push(paginationItem(options, num - 1, "&raquo;"));
        }
      }
      return pagination.show().empty().append("<ul>" + (markup.join('')) + "</ul>");
    };
    alignTable = function(options) {
      var numCol, parent, rowsToAdd;
      rowsToAdd = options.itemsPerPage - realMod(getRows(options).length, options.itemsPerPage);
      parent = options.table.find(options.childSelector).parent();
      numCol = $(options.table.find(options.childSelector)[0]).children().length;
      return parent.append(Array(rowsToAdd + 1).join('<tr><td colspan="' + numCol + '">&ensp;</td></tr>'));
    };
    realMod = function(n, base) {
      var jsmod;
      if (!((jsmod = n % base) && ((n > 0) !== (base > 0)))) {
        return jsmod;
      } else {
        return jsmod + base;
      }
    };
    paginationRange = function(options, size) {
      var end, start, _i, _j, _results, _results1;
      if (options.paginationSize >= size) {
        return (function() {
          _results = [];
          for (var _i = 0; 0 <= size ? _i < size : _i > size; 0 <= size ? _i++ : _i--){ _results.push(_i); }
          return _results;
        }).apply(this);
      } else {
        start = Math.max(0, options.currentPage - Math.floor(options.paginationSize / 2));
        end = start + options.paginationSize;
        if (end > size) {
          start = start + size - end;
          end = size;
        }
        return (function() {
          _results1 = [];
          for (var _j = start; start <= end ? _j < end : _j > end; start <= end ? _j++ : _j--){ _results1.push(_j); }
          return _results1;
        }).apply(this);
      }
    };
    paginationItem = function(options, value, name) {
      var cssClass;
      cssClass = value === options.currentPage ? 'active' : '';
      return "<li class='" + cssClass + "'><a href='#' data-page-num='" + value + "'>" + name + "</a></li>";
    };
    return $.extend(jQuery.fn, {
      tableNav: tableNav
    });
  })(window.jQuery);

}).call(this);

/*
//@ sourceMappingURL=bootstrap-table-nav.map
*/