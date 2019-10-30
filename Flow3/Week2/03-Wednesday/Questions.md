# Questions Wednesday
---
Q: **What is meant by the react term “Lifting State Up”?**
A: Lifting State up means to send the data to the closest common ancestor. If we want to cahnge the state and reflect the state we use a mother component with functions, and send these function down to the componets through props.
---
Q: **Where do you lift it up to?**
A: To the closest common ancestor.
---
Q: **Which way does React recommend data to flow: From sibling to sibling, from bottom to top or from top to bottom?**
A: Bottom to top, closetes common ancestor
---
Q: **Lifting state up, involves a great deal of boilerplate code, what’s the benefit we get from doing “things” like this?**
A: To have a standart template for usage with Lifting state.
---