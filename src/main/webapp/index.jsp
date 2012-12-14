<html>
<body>
<h2>Hello World!</h2>
<script>

    function plainArray() {
        return [];
    }

    var arrayWithProperty;
    if ([].__proto__) {
        function ArrayWithProperty() {
            var list = [];
            list.push.apply(list, arguments);
            list.__proto__ = this.__proto__;
            return list;
        }

        ArrayWithProperty.prototype = {
            __proto__ : Array.prototype,
            foo : 123
        };

        arrayWithProperty = function() {
            return new ArrayWithProperty();
        }
    } else {
        arrayWithProperty = function () {
            var a = [];
            a.foo = 123;
            return a;
        }
    }

    function test(a) {
        for (var i = 0; i < 10000; i++) {
            a[i] = i;
        }
        var sum = 0;
        for (var i = 0; i < a.length; i++) {
            sum += a[i];
        }
        return sum;
    }
</script>

</body>
</html>
