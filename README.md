#  Open FPGA Stack (OFS) software layer for Yocto

This layer provides the [Linux DFL kernel](https://github.com/OFS/linux-dfl)
and the [OPAE SDK](https://github.com/OFS/opae-sdk) for the Yocto Project\*.

## Building the IOTG Yocto-based ESE example

Create and initialize the source directory:

```
mkdir ofs-yocto && cd ofs-yocto
repo init -m examples/iotg-yocto-ese/manifest.xml https://github.com/OFS/meta-ofs
```

Fetch repositories and update the working tree:

```
repo sync -j 16
```

Build packages and create an image:

```
cd build
. ../intel-embedded-system-enabling/oe-init-build-env .
bitbake mc:x86-2021-minimal:core-image-full-cmdline
```
