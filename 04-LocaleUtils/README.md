Add Test For LocaleUtils

* <pre>
*   LocaleUtils.toLocale("")           = new Locale("", "")
*   LocaleUtils.toLocale("en")         = new Locale("en", "")
*   LocaleUtils.toLocale("en_GB")      = new Locale("en", "GB")
*   LocaleUtils.toLocale("en_GB_xxx")  = new Locale("en", "GB", "xxx")
    …………………………………………………………
* </pre>