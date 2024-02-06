SUMMARY = "Backport version of the linux-dfl (Device Feature List) kernel driver for FPGA devices"
DESCRIPTION = "${SUMMARY}"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit module

#SRC_URI = "git://github.com/OFS/linux-dfl-backport;protocol=https;branch=intel/fpga-ofs-dev-6.6-lts file://yocto-kernel-builddir.patch"
SRC_URI = "git://github.com/OFS/linux-dfl-backport;protocol=https;branch=pcolberg/dfhv1 file://yocto-kernel-builddir.patch"
SRCREV = "${AUTOREV}"
S = "${WORKDIR}/git"
MODULES_INSTALL_TARGET = "install"
EXTRA_OEMAKE += "KERNELDIR=${STAGING_KERNEL_DIR} KERNEL_BUILDDIR=${STAGING_KERNEL_BUILDDIR}"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

RPROVIDES:${PN} += "kernel-module-intel-fpga-dfl"

FILESEXTRAPATHS:prepend := "${THISDIR}:"
