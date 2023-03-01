# Appl
This application enables two tables one is mobiles the other is cart.

URL's to modify Mobile Model using controllers:
1. http://localhost:8080/api/v2/mobiles :
    @POST It will add the mobile details to mobiles table
2. http://localhost:8080/api/v2/mobiles/id/{id}
    @GET It will display mobile with given id.
3. http://localhost:8080/api/v2/mobiles/name/{name}
    @GET It will display a list of all mobiles which contains name in its name field
4. http://localhost:8080/api/v2/mobiles
    @GET It will display a list of all mobiles existing.
5. http://localhost:8080/api/v2/mobiles/update/{id}
    @PUT It will update the given details into specified mobile id.
6. http://localhost:8080/api/v2/mobiles/delete/{id}
    @DELETE It will delete the mobile of given id.


URL's to modify Mobile Model using controllers:
1. http://localhost:8080/api/v3/cart/create :
    @POST It will create a cart with given id.
2. http://localhost:8080/api/v3/cart/add/mobile/{id}
    @POST It will add given mobile id to cart
3. http://localhost:8080/api/v3/cart/add/{cartId}/mobile/
    @POST It will add pushed mobile id into cart with given cart id.
4. http://localhost:8080/api/v3/cart/show
    @GET It will display a list of all cart items with cart ids and mobiles in each cart id
5. http://localhost:8080/api/v3/cart/show/{cartId}
    @GET It will display mobiles added to given cart id.
6. http://localhost:8080/api/v3/cart/delete/{cartId}
    @DELETE It will delete an entire cart with given id
7. http://localhost:8080/api/v3/cart/delete/{cartId}/mobile/
    @DELETE It will delete specified mobile id from the given cart id.
