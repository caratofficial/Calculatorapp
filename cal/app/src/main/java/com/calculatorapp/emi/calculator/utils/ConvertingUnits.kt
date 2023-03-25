package com.calculatorapp.emi.calculator.utils


class ConvertingUnits {
    class Length {
        fun milliToMeter(milli: Double): Double {
            return milli / 1000
        }

        fun meterToMilli(meter: Double): Double {
            return meter * 1000
        }

        fun CentiToMeter(Centi: Double): Double {
            return Centi / 100
        }

        fun MeterToCenti(meter: Double): Double {
            return meter * 100
        }

        fun KiloToMeter(Kilo: Double): Double {
            return Kilo * 1000
        }

        fun MeterToKilo(meter: Double): Double {
            return meter / 1000
        }

        fun InchToMeter(Inch: Double): Double {
            return Inch / 39.3701
        }

        fun MeterToInch(meter: Double): Double {
            return meter * 39.3701
        }

        fun FootToMeter(Foot: Double): Double {
            return Foot / 3.28084
        }

        fun MeterToFoot(meter: Double): Double {
            return meter * 3.28084
        }

        fun YardToMeter(Yard: Double): Double {
            return Yard / 1.09361
        }

        fun MeterToYard(meter: Double): Double {
            return meter * 1.09361
        }

        fun MileToMeter(Mile: Double): Double {
            return Mile / 0.000621371
        }

        fun MeterToMile(meter: Double): Double {
            return meter * 0.000621371
        }

        fun NanoToMeter(milli: Double): Double {
            return milli / 1000000000
        }

        fun MeterToNano(meter: Double): Double {
            return meter * 1000000000
        }
    }
}