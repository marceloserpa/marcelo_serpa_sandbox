FROM rust:1.31 as build

RUN USER=root cargo new --bin rust-actix-api
WORKDIR /rust-actix-api

# copy over your manifests
COPY ./Cargo.lock ./Cargo.lock
COPY ./Cargo.toml ./Cargo.toml

# this build step will cache your dependencies
RUN cargo build --release
RUN rm src/*.rs

# copy your source tree
COPY ./src ./src

# build for release
RUN rm ./target/release/deps/rust-actix-api*
RUN cargo build --release

# our final base
FROM rust:1.31

# copy the build artifact from the build stage
COPY --from=build /rust-actix-api/target/release/rust-actix-api .

# set the startup command to run your binary
CMD ["./rust-actix-api"]