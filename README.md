## 🚀Amazon Selenium Test:

Create a Simple Amazon Test.
### Technologies used:

- Java 8+
- Intellij 
- TestNG
- Selenium

### Steps:

1. Create new Maven Project on Intellij.
2. To use TestNG and Selenium Search for the Required Dependencies on [Maven Repository](https://mvnrepository.com/).
3. Navigates to the [Amazon Website](https://www.amazon.com/).
4. My default location is Israel, so I will change it to be USA.
5. Search for 'Samsung Galaxy' and Verify that returned List contains what im Looking for.
6. Select Random Product from that List and added Product to cart.
7. Asserts:
- Verify cart contains 0 Products in the begining of test.
- Verify the title of the selected Product match to at least one title from the returned Products list.
- Verify the price on cart matches the price for the selected Product.
- Verify cart contains 1 Product in the end of test.
