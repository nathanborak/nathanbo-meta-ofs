SUMMARY = "Altera I2C Driver Test Install on Yocto"
DESCRIPTION = "${SUMMARY}"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit module

SRC_URI = "file://i2c-altera.c \
	   file://Makefile"

SRCREV = "${AUTOREV}"
S = "${WORKDIR}/git"
MODULES_INSTALL_TARGET = "install"
EXTRA_OEMAKE += "KERNELDIR=${STAGING_KERNEL_DIR} KERNELBUILDDIR=${STAGING_KERNEL_BUILDDIR}"

# Do not split modules into separate, per-module packages such as
# kernel-module-fpga-bridge-6.1.55-altera, which will result in
# build failure due to conflicting .packagedata file entries if
# the kernel recipe builds the same module packages already.
KERNEL_SPLIT_MODULES = "0"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

RPROVIDES:${PN} += "i2c-altera"

MACHINE_EXTRA_RRECOMMENDS += "i2c-altera"
