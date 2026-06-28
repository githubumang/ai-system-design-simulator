import { Brain, Layers, BarChart3, Network } from "lucide-react"

const features = [
  {
    icon: Brain,
    title: "AI-Powered Feedback",
    description: "Receive structured feedback, scores, strengths, weaknesses, and improvement suggestions.",
  },
  {
    icon: Layers,
    title: "Real Interview Questions",
    description: "Practice industry-standard system design and backend engineering questions.",
  },
  {
    icon: BarChart3,
    title: "Track Progress",
    description: "Monitor submissions, evaluations, and overall learning progress.",
  },
  {
    icon: Network,
    title: "Structured Learning",
    description: "Learn functional requirements, scalability, APIs, databases, and trade-offs.",
  },
]

export function FeaturesSection() {
  return (
    <section id="features" className="mx-auto max-w-[1280px] px-4 py-20 sm:px-6 lg:py-28 lg:px-8">
      <div className="mx-auto max-w-2xl text-center">
        <h2 className="text-balance text-3xl font-semibold tracking-tight sm:text-4xl">
          Everything You Need to Ace System Design Interviews
        </h2>
        <p className="mt-4 text-pretty text-base leading-relaxed text-muted-foreground">
          A complete toolkit to practice, evaluate, and level up your system design skills.
        </p>
      </div>

      <div className="mt-14 grid grid-cols-1 gap-6 sm:grid-cols-2 lg:grid-cols-4">
        {features.map((feature) => (
          <div
            key={feature.title}
            className="group rounded-2xl border border-border bg-card p-6 shadow-sm transition-all duration-300 hover:-translate-y-1 hover:border-primary/40 hover:shadow-lg"
          >
            <span className="inline-flex h-11 w-11 items-center justify-center rounded-xl bg-accent text-accent-foreground transition-colors group-hover:bg-primary group-hover:text-primary-foreground">
              <feature.icon className="h-5 w-5" aria-hidden="true" />
            </span>
            <h3 className="mt-5 text-base font-semibold tracking-tight">{feature.title}</h3>
            <p className="mt-2 text-sm leading-relaxed text-muted-foreground">{feature.description}</p>
          </div>
        ))}
      </div>
    </section>
  )
}
