@file:Suppress("DEPRECATION")

package io.github.cdsap.td.paparazzi

import app.cash.paparazzi.SnapshotHandler
import app.cash.paparazzi.detectMaxPercentDifferenceDefault

/**
 * Returns a Test Distribution-compatible [SnapshotHandler] that can be passed
 * directly to the Paparazzi constructor.
 *
 * This decouples the library from the Paparazzi constructor signature, allowing
 * it to work with any Paparazzi version.
 *
 * Usage:
 * ```
 * @get:Rule
 * val paparazzi = Paparazzi(
 *     snapshotHandler = tdSnapshotHandler()
 * )
 * ```
 *
 * @param maxPercentDifference threshold used by `SnapshotVerifier` in verify
 *   mode, and by upstream `HtmlReportWriter` in record mode when
 *   `-Dpaparazzi.test.record.overwriteOnMaxPercentDifference=true` is set.
 *   Defaults to Paparazzi's own [detectMaxPercentDifferenceDefault] (currently
 *   `0.01`, also overridable via `-Dapp.cash.paparazzi.maxPercentDifferenceDefault`).
 * @param fileNameProvider ignored; retained for source compatibility. Golden
 *   filenames are now produced by Paparazzi itself.
 */
@JvmOverloads
fun tdSnapshotHandler(
    maxPercentDifference: Double = detectMaxPercentDifferenceDefault(),
    @Suppress("UNUSED_PARAMETER")
    fileNameProvider: SnapshotFileNameProvider = DefaultSnapshotFileNameProvider
): SnapshotHandler =
    TDPaparazziHandlerProvider().determineHandler(maxPercentDifference)
