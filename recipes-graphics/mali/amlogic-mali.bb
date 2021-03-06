SUMMARY = "Mali Video driver for ${MACHINE}"
SECTION = "base"
PRIORITY = "required"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://Makefile;md5=d0d2f45bce10dd67cca4a749d12e535e"

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "^(alien5|k1pro|k2pro|k2prov2|k3pro|k1plus|k1plusv2)$"

SRC_URI[md5sum] = "b849349480679ca6253cf2660e366ec6"

SRC_URI = "http://sources.libreelec.tv/devel/gpu-aml-r6p1-01rel0-2364187.tar.xz"

S = "${WORKDIR}/gpu-aml-r6p1-01rel0-2364187/mali"

inherit module

EXTRA_OEMAKE = "CONFIG_MALI400=m CONFIG_MALI450=m CONFIG_MALI470=m KDIR=${STAGING_KERNEL_BUILDDIR}"

do_compile() {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
	oe_runmake -C "${STAGING_KERNEL_BUILDDIR}" M="${S}" modules
}

do_install() {
	install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/gpu/mali
	install -m 0644 ${S}/mali.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/gpu/mali
}
