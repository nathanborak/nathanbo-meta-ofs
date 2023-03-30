#  Open FPGA Stack (OFS) software layer for Yocto

This layer provides the [Linux DFL kernel](https://github.com/OFS/linux-dfl)
and the [OPAE SDK](https://github.com/OFS/opae-sdk) for the [Yocto
Project\*](https://www.yoctoproject.org/).

## Getting started with Yocto

If you are new to the Yocto Project\*, start with the [overview and concepts
manual](https://docs.yoctoproject.org/overview-manual/), with more detailed
information available in the [reference
manual](https://docs.yoctoproject.org/ref-manual/) and the [Linux kernel
development manual](https://docs.yoctoproject.org/kernel-dev/).
To prepare a machine for building Yocto images, see the [system
requirements](https://docs.yoctoproject.org/ref-manual/system-requirements.html),
in particular the [required packages for the build
host](https://docs.yoctoproject.org/ref-manual/system-requirements.html#required-packages-for-the-build-host).
The [variables
glossary](https://docs.yoctoproject.org/ref-manual/variables.html) explains the
common variables used in the build system.

## Building the IOTG Yocto-based ESE example

This example takes the [IOTG Yocto-based ESE
BSP](https://github.com/intel/iotg-yocto-ese-manifest) as a basis
and substitutes the [Linux DFL kernel](https://github.com/OFS/linux-dfl)
including the latest [DFL drivers for FPGA
devices](https://docs.kernel.org/fpga/dfl.html) along with the [OPAE
SDK](https://github.com/OFS/opae-sdk) user space.
The image targets `x86_64` SoC FPGA devices but should boot on most UEFI-based
machines.

The build needs more than 100 GiB of disk space. As a reference point, on a
system with two Intel(R) Xeon(R) E5-2699 v4 for a total of 44 CPU cores,
the initial, non-incremental build takes less than an hour of wall time.

The [`repo`](https://gerrit.googlesource.com/git-repo#install) tool is
needed to clone the various [Yocto layer
repositories](https://github.com/OFS/meta-ofs/blob/main/examples/iotg-yocto-ese/manifest.xml)
used in this example.

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

The resulting GPT disk image is available in uncompressed (`.wic`) and
compressed form (`.wic.gz`) in
`build/tmp-x86-2021-minimal-glibc/deploy/images/intel-corei7-64/`.

The [image type](https://docs.yoctoproject.org/ref-manual/images.html)
`core-image-full-cmdline` includes the familiar GNU core utilities,
as opposed to `core-image-minimal` which uses BusyBox instead.

The example build configuration files under `build/conf/` are symlinked from
[`examples/iotg-yocto-ese/`](https://github.com/OFS/meta-ofs/tree/main/examples/iotg-yocto-ese).
To customise the image, start by modifying
[`local.conf`](https://github.com/OFS/meta-ofs/tree/main/examples/iotg-yocto-ese/local.conf)
and
[`bblayers.conf`](https://github.com/OFS/meta-ofs/tree/main/examples/iotg-yocto-ese/bblayers.conf).

## License

All metadata files (including, but not limited to `.bb`, `.bbappend`,
`.bbclass`, `.inc` and `.conf` files) are [MIT
licensed](https://github.com/OFS/meta-ofs/blob/main/COPYING.MIT) unless
otherwise stated. Source code included in tree for individual recipes is under
the LICENSE stated in the associated recipe (`.bb` file) unless otherwise
stated.
