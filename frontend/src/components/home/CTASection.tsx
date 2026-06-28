import { Link } from "react-router-dom"
import { ArrowRight } from "lucide-react"

export function CTASection() {
  return (
    <section id="cta" className="mx-auto max-w-[1280px] px-4 py-20 sm:px-6 lg:py-28 lg:px-8">
      <div className="relative overflow-hidden rounded-3xl border border-border bg-primary px-6 py-16 text-center shadow-xl sm:px-12 lg:py-20">
        <div
          aria-hidden="true"
          className="pointer-events-none absolute -right-20 -top-20 h-64 w-64 rounded-full bg-primary-foreground/10 blur-3xl"
        />
        <div
          aria-hidden="true"
          className="pointer-events-none absolute -bottom-24 -left-16 h-64 w-64 rounded-full bg-primary-foreground/10 blur-3xl"
        />
        <h2 className="mx-auto max-w-2xl text-balance text-3xl font-semibold tracking-tight text-primary-foreground sm:text-4xl">
          Ready to Improve Your System Design Skills?
        </h2>
        <p className="mx-auto mt-4 max-w-xl text-pretty text-base leading-relaxed text-primary-foreground/80">
          Start solving real interview questions and receive instant AI feedback.
        </p>
        <div className="mt-8 flex justify-center">
          <Link
            to="/questions"
            className="group inline-flex items-center gap-1.5 rounded-full bg-background px-6 py-3 text-sm font-medium text-foreground shadow-sm transition-all hover:shadow-md"
          >
            Start Practicing
            <ArrowRight className="h-4 w-4 transition-transform group-hover:translate-x-0.5" aria-hidden="true" />
          </Link>
        </div>
      </div>
    </section>
  )
}
