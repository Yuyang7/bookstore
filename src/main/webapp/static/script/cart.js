window.onload = function () {
    let vue = new Vue({
        el: "#cart_div",
        data: {
            cart: {}
        },

        methods: {
            deleteCart: function () {
                var form = document.getElementById("deleteForm");
                var href = event.target.href;
                form.action = href;
                form.submit();
            },
            getCart() {
                axios.get(
                    "/book/cart/get"
                ).then(response => {
                    let data = response.data;
                    vue.cart = data;
                }).catch(reason => {
                })
            },
            editCartItem: function (cartItemId, buyCount) {
                if (buyCount < 0) {
                    return;
                }
                axios.put(
                    "/book/cart/put/" + cartItemId + "/" + buyCount
                )
                    .then(Response => {
                        vue.getCart();
                    }).catch(reason => {
                    console.log(reason.message)
                })
            },
            deleteCartItem: function (cartItemId) {

                axios({
                    method:"delete",
                    url:"/book/cart/deleteCartItem/"+cartItemId,
                })
                    .then(Response => {
                        vue.getCart();
                    }).catch(reason => {
                    console.log(reason.message)
                })
            }
        },


        mounted: function () {
            this.getCart();
        }
    });
}
