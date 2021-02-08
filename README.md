# Gift Shop

- [Gift Shop](#gift-shop)
  - [Steps to Start Application Locally](#steps-to-start-application-locally)
  - [Screenshots](#screenshots)
    - [Auth](#auth)
    - [Home](#home)
    - [Cart](#cart)
    - [Orders](#orders)
    - [Admin](#admin)
  - [Configure Jenkins](#configure-jenkins)

## Steps to Start Application Locally

1. Launch Database Server
2. Launch Discovery Server
3. Launch Other Services
4. **NOTE:** Make sure Profiles in `src/main/resources/application.yml` is set to `dev`

## Screenshots

### Auth

![Login](./screenshots/login.png)
![Register](./screenshots/register.png)
![Edit Profile](./screenshots/edit_profile.png)

### Home

![Home](./screenshots/home.png)
![Home2](./screenshots/home2.png)
![Search](./screenshots/search.png)
![Product Details](./screenshots/product_details.png)

### Cart

![Cart](./screenshots/cart.png)
![Cart Empty](./screenshots/empty_cart.png)
![Shipping](./screenshots/shipping.png)
![Payment](./screenshots/payment.png)
![Payment2](./screenshots/razorpay.png)
![Payment Success](./screenshots/payment_success.png)

### Orders

![Order History](./screenshots/order_history.png)
![Delivery History](./screenshots/delivery_history.png)
![Payment](./screenshots/payment.png)

### Admin

![Add Category](./screenshots/add_category.png)
![Add Product](./screenshots/add_product.png)
![View Category](./screenshots/admin_category.png)
![View Products](./screenshots/admin_product.png)

## Configure Jenkins

1. Navigate to jenkins > Manage jenkins > In-process Script Approval
2. Install Manage-pipeline-utility
3. Create new Project
   1. New Project
   2. Select Git
   3. Set path as service-name/Jenkinsfile
   4. Select multistage pipeline
   5. Save
