# socketioxide v4 binary bug reproducer

This reproduces an issue where socketioxide, when acting as a v4 server,
fails to receive binary events properly.  The `Vec<u8>` passed to the
handler contains the correct binary data, but it is prefixed with a
`0x4` byte.

## To demonstrate:

0. Prerequisites:
   * A cargo/rust compiler environment
   * JDK 11 or greater, and maven
1. In one terminal, run `make run-server`
2. In another terminal, run `make run-client`
3. Assuming both have compiled and run successfully, switch back to the
   first terminal and watch for output.

## What actually happens

We see:

```
Got binary data: [4, 98, 105, 110, 97, 114, 121]
```

## What's expected

But we *should* see:

```
Got binary data: [98, 105, 110, 97, 114, 121]
```
